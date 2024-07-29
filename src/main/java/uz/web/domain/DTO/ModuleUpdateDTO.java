package uz.web.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ModuleUpdateDTO {
    private UUID id;
    private String name;
    private String description;
    private Integer orderNum;

}
