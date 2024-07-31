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
@Table(name = "courses")
public class CourseEntity extends BaseEntity {
    private String name;
    private String description;
    private Double price;
    private String imageId;

    @JoinColumn(name = "mentor_id")
    @ManyToOne
    private MentorEntity mentor;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "course")
    private List<ModuleEntity> moduleEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", fetch = FetchType.EAGER)
    private List<RatingEntity> ratingEntities;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "course")
    private List<CommentEntity> commentEntities;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "course")
    private List<CoursesOfUsersEntity> coursesOfUsersEntities;
}
