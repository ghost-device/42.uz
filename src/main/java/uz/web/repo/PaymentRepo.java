package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.PaymentEntity;
import uz.web.domain.enumerators.PaymentStatus;

import java.util.List;

@Repository
public class PaymentRepo extends BaseRepo<PaymentEntity> {
    public PaymentRepo() {
        super.aClass = PaymentEntity.class;
    }

    public List<PaymentEntity> getAll() {
        return entityManager.createQuery("from PaymentEntity", PaymentEntity.class).getResultList();
    }

    public List<PaymentEntity> getPaymentsByStatus(PaymentStatus status) {
        return entityManager.createQuery("from PaymentEntity p where p.status = :st", PaymentEntity.class).setParameter("st", status).getResultList();
    }
}
