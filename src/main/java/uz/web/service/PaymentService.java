package uz.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.web.domain.DTO.PaymentDTO;
import uz.web.domain.entity.PaymentEntity;
import uz.web.domain.enumerators.PaymentStatus;
import uz.web.repo.PaymentRepo;

@Service
public class PaymentService extends BaseService<PaymentEntity, PaymentRepo> {
    private UserService userService;

    @Transactional
    public void payForCourse(PaymentDTO payment) {
        repo.save(PaymentEntity.builder()
                .user(userService.findById(payment.getId()))
                .amount(payment.getAmount())
                .recipeUrl(payment.getRecipeUrl())
                .status(PaymentStatus.PENDING)
                .build());
    }

}
