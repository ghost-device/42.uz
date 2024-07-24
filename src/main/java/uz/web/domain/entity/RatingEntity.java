package uz.web.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "ratings")
public class RatingEntity extends BaseEntity {
    @JoinColumn
    @ManyToOne
    private CourseEntity course;

    @JoinColumn
    @ManyToOne
    private UserEntity user;

    private int rating;
}
