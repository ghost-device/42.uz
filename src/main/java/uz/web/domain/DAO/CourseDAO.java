package uz.web.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDAO {
    private UUID id;
    private String name;
    private String description;
    private String mentor;
    private Double price;
    private String imageUrl;
}
