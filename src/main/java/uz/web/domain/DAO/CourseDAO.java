package uz.web.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDAO {
    private String name;
    private String description;
    private String mentor;
    private boolean isActive;
    private Double price;
    private String imageUrl;
}
