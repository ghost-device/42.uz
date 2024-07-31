package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.DAO.PaymentHistoryDAO;
import uz.web.domain.entity.PaymentEntity;
import uz.web.domain.enumerators.PaymentStatus;

import java.util.List;
import java.util.UUID;

@Repository
public class PaymentRepo extends BaseRepo<PaymentEntity> {
    public PaymentRepo() {
        super.aClass = PaymentEntity.class;
    }

    public List<PaymentEntity> getAll(PaymentStatus paymentStatus) {
        return entityManager.createQuery("from PaymentEntity where status = :paymentStatus", PaymentEntity.class).setParameter("paymentStatus", paymentStatus).getResultList();
    }

    public List<PaymentEntity> getAll() {
        return entityManager.createQuery("from PaymentEntity where status != 'PENDING'", PaymentEntity.class).getResultList();
    }

    public List<PaymentEntity> getAllPaymentsByUser(UUID userId){
        return entityManager.createQuery("from PaymentEntity p where p.user.id = :userId", PaymentEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<PaymentEntity> getPaymentsByStatus(PaymentStatus status) {
        return entityManager.createQuery("from PaymentEntity p where p.status = :st", PaymentEntity.class).setParameter("st", status).getResultList();
    }
}
