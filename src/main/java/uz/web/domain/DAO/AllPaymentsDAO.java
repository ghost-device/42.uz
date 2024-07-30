package uz.web.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.web.domain.enumerators.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AllPaymentsDAO {
    private UUID id;
    private String pictureUrl;
    private Double amount;
    private String email;
    private LocalDateTime date;
    private PaymentStatus status;
}
