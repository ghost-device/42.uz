package uz.web.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LessonForModuleDAO {
    private UUID id;
    private String name;
    private String description;
    private int orderNum;
    private int videoDuration;
}
