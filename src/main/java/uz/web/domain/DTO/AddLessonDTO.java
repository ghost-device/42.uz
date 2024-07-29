package uz.web.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class AddLessonDTO {
    private UUID moduleId;
    private String lessonName;
    private Integer orderNum;
    private String lessonDescription;
    private Integer videoDuration;
}
