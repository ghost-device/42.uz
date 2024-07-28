package uz.web.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePasswordDTO {
    private UUID id;
    private String oldPassword;
    private String newPassword;
}
