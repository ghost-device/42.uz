package uz.web.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
