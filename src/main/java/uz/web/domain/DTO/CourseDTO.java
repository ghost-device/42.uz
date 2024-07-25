package uz.web.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CourseDTO {
    private String name;
    private String description;
    private Integer price;
    private MultipartFile faceImgOfCourse;
    private UUID mentorId;
}
