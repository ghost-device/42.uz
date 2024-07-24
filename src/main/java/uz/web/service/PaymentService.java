package uz.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.web.domain.DAO.PaymentHistoryDAO;
import uz.web.domain.DTO.PaymentDTO;
import uz.web.domain.entity.PaymentEntity;
import uz.web.domain.entity.UserEntity;
import uz.web.domain.enumerators.PaymentStatus;
import uz.web.domain.exceptions.UserNotFoundException;
import uz.web.repo.PaymentRepo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService extends BaseService<PaymentEntity, PaymentRepo> {
    private UserService userService;

    @Transactional
    public void purchaseCourse(PaymentDTO payment) {
        repo.save(PaymentEntity.builder()
                .user(userService.findById(payment.getId()))
                .amount(payment.getAmount())
                .recipeUrl(payment.getRecipeUrl())
                .status(PaymentStatus.PENDING)
                .build());
    }

    public List<PaymentHistoryDAO> paymentHistory(UUID userId) {
        UserEntity user = userService.findById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        List<PaymentEntity> paymentEntities = user.getPaymentEntities();
        List<PaymentHistoryDAO> paymentHistory = new ArrayList<>();
        for (PaymentEntity payment : paymentEntities) {
            paymentHistory.add(PaymentHistoryDAO.builder()
                    .status(payment.getStatus())
                    .amount(payment.getAmount())
                    .paymentDate(payment.getUpdatedAt())
                    .build());
        }
        paymentHistory.sort(Comparator.comparing(PaymentHistoryDAO::getPaymentDate));

        return paymentHistory;
    }

}
