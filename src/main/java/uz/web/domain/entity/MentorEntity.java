package uz.web.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "mentors")
public class MentorEntity extends BaseEntity {
    private String name;
    private String biography;
    private String pictureId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mentor")
    private List<CourseEntity> courseEntities;
}
