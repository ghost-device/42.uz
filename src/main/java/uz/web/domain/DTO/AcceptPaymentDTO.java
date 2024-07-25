package uz.web.domain.DTO;

import lombok.*;
import uz.web.domain.enumerators.PaymentStatus;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class AcceptPaymentDTO {
    private UUID buyerId;
    private UUID courseId;
    private PaymentStatus status;
}
