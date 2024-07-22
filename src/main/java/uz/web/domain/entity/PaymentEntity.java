package uz.web.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.web.domain.enumerators.PaymentStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "payments")
public class PaymentEntity extends BaseEntity {
    @JoinColumn
    @ManyToOne
    private UserEntity user;

    private Integer amount;

    private PaymentStatus status;
}
