package uz.web.domain.DAO;

import lombok.*;
import uz.web.domain.enumerators.PaymentStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaymentHistoryDAO {
    private Integer amount;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    private String paymentCheckId;
}
