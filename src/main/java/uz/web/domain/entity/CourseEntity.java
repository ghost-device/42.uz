package uz.web.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class CourseEntity extends BaseEntity {
    private String name;
    private String description;
    private Integer price;
    private String imageId;

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
