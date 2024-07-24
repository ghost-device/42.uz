package uz.web.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.web.domain.enumerators.PaymentStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "payments")
public class PaymentEntity extends BaseEntity {
    @JoinColumn
    @ManyToOne
    private UserEntity user;

    private Integer amount;

    private String paymentCheckId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
