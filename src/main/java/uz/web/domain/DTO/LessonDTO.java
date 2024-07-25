package uz.web.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LessonDTO {
    private String name;
    private UUID moduleId;
    private int orderNum;
    private MultipartFile videoOfLesson;
    private int videoDuration;
}
