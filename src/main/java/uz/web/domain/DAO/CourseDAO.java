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
    private String mentorName;
    private boolean isActive;
    private Integer price;
    private String imageUrl;
}
