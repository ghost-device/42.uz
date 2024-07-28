package uz.web.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {
    private String name;
    private String description;
    private UUID courseId;
    private int orderNum;
}
