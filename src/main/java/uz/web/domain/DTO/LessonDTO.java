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
    private UUID id;
    private String name;
    private int orderNum;
    private int videoDuration;
    private String description;
}
