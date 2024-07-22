package uz.web.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {
    @JoinColumn
    @ManyToOne
    private UserEntity user;

    @JoinColumn
    @ManyToOne
    private CourseEntity course;

    private String comment;
}
