package uz.web.domain.DAO;

import jakarta.persistence.EntityManager;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticDAO {

    private Integer courseNumber;
    private Integer mentorNumber;
    private Integer studentNumber;
}
