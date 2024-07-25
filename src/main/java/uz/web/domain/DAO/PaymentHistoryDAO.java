package uz.web.domain.DAO;

import lombok.*;
import uz.web.domain.enumerators.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaymentHistoryDAO {
    private UUID userId;
    private Integer amount;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    private String paymentCheckId;
}
