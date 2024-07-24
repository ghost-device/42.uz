package uz.web.domain.DTO;

import jakarta.mail.Multipart;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private UUID id;
    private Integer amount;
    private Multipart multipart;
}
