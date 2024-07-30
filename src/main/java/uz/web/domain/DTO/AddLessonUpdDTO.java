package uz.web.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AddLessonUpdDTO {
    private UUID id; // moduleId or lessonId
    private String name;
    private String lessonDescription;
    private int orderNum;
    private int videoDuration;
}
