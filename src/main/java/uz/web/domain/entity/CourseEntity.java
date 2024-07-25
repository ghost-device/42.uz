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
    private Integer price;
    private String imageId;
    private boolean isActive;

    @JoinColumn(name = "mentor_id")
    @ManyToOne
    private MentorEntity mentor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "course")
    private List<ModuleEntity> moduleEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<RatingEntity> ratingEntities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "course")
    private List<CommentEntity> commentEntities;

    @OneToMany(mappedBy = "course")
    private List<CoursesOfUsersEntity> coursesOfUsersEntities;
}
