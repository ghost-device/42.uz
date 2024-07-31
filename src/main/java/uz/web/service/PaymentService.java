package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DAO.AllPaymentsDAO;
import uz.web.domain.DAO.PaymentHistoryDAO;
import uz.web.domain.DTO.AcceptPaymentDTO;
import uz.web.domain.DTO.PaymentDTO;
import uz.web.domain.entity.PaymentEntity;
import uz.web.domain.entity.UserEntity;
import uz.web.domain.enumerators.PaymentStatus;
import uz.web.domain.exceptions.CourseNotFoundException;
import uz.web.domain.exceptions.UserNotFoundException;
import uz.web.repo.PaymentRepo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Scope("singleton")
public class PaymentService extends BaseService<PaymentEntity> {
    private final PaymentRepo paymentRepo;
    private final UserService userService;
    private final CloudService cloudService;

    @Transactional
    public void fillBalance(PaymentDTO payment, MultipartFile multipartFile) {
        paymentRepo.save(PaymentEntity.builder()
                .user(userService.findById(payment.getUserId()))
                .amount(payment.getAmount())
                .paymentCheckId(cloudService.uploadFile(multipartFile))
                .status(PaymentStatus.PENDING)
                .build());
        UserEntity user = userService.findById(payment.getUserId());
        user.setBalance(user.getBalance() + payment.getAmount());
        userService.update(user);
    }

    public List<PaymentHistoryDAO> paymentHistoryOfUser(UUID userId) {
        UserEntity user = userService.findById(userId);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        List<PaymentEntity> paymentEntities = user.getPaymentEntities();
        List<PaymentHistoryDAO> paymentHistory = new ArrayList<>();

        for (PaymentEntity payment : paymentEntities) {
            paymentHistory.add(PaymentHistoryDAO.builder()
                    .paymentCheckId(cloudService.getFileUrl(payment.getPaymentCheckId()))
                    .status(payment.getStatus())
                    .amount(payment.getAmount())
                    .paymentDate(payment.getUpdatedAt())
                    .build());
        }

        paymentHistory.sort(Comparator.comparing(PaymentHistoryDAO::getPaymentDate));

        return paymentHistory;
    }

    public String paymentCheckUrl(String paymentCheckId) {
        return cloudService.getFileUrl(paymentCheckId);
    }

    public List<AllPaymentsDAO> allPayments(PaymentStatus paymentStatus) {
        List<PaymentEntity> all = paymentRepo.getAll(paymentStatus);
        return getAllPaymentsDAOS(all);
    }

    public List<AllPaymentsDAO> allPayments() {
        List<PaymentEntity> all = paymentRepo.getAll();
        return getAllPaymentsDAOS(all);
    }

    private static List<AllPaymentsDAO> getAllPaymentsDAOS(List<PaymentEntity> all) {
        List<AllPaymentsDAO> allPayments = new ArrayList<>();
        for (PaymentEntity payment : all) {

            allPayments.add(new AllPaymentsDAO(
                    payment.getId(), payment.getPaymentCheckId(),
                    payment.getAmount().doubleValue(), payment.getUser().getEmail(),
                    payment.getCreatedAt(), payment.getStatus()
            ));
        }
        return allPayments;
    }

    public List<PaymentEntity> userPayments(PaymentHistoryDAO paymentHistoryDAO) {
        return paymentRepo.getAllPaymentsByUser(paymentHistoryDAO.getUserId());
    }


    @Transactional
    public void acceptPayment(AcceptPaymentDTO acceptPayment) {
        if (userService.findById(acceptPayment.getBuyerId()) == null) {
            throw new UserNotFoundException("User not found");
        }
        if (userService.findById(acceptPayment.getCourseId()) == null) {
            throw new CourseNotFoundException("Course not found");
        }
        List<PaymentEntity> payments = paymentRepo.getPaymentsByStatus(acceptPayment.getStatus());
        for (PaymentEntity payment : payments) {
            if (payment.getStatus() == PaymentStatus.PENDING) {
                UserEntity user = userService.findById(acceptPayment.getBuyerId());
                user.setBalance(user.getBalance() - payment.getAmount());
                payment.setStatus(PaymentStatus.ACCEPTED);
                this.update(payment);
                userService.setUserBalanceById(user.getId(), payment.getAmount());
                break;
            }
        }
    }

    @Transactional
    public void canceledPayment(AcceptPaymentDTO acceptPaymentDTO) {
        List<PaymentEntity> paymentsByStatus = paymentRepo.getPaymentsByStatus(acceptPaymentDTO.getStatus());
        for (PaymentEntity payments : paymentsByStatus) {
            if (payments.getStatus().equals(PaymentStatus.PENDING)) {
                payments.setStatus(PaymentStatus.CANCELED);
                this.update(payments);
                break;
            }
        }
    }


    @Override
    public void save(PaymentEntity paymentEntity) {
        paymentRepo.save(paymentEntity);
    }

    @Override
    public PaymentEntity findById(UUID id) {
        return paymentRepo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        paymentRepo.delete(id);
    }

    @Override
    public void update(PaymentEntity paymentEntity) {
        paymentRepo.update(paymentEntity);
    }
}