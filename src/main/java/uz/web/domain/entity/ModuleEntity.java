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
@Table(name = "modules")
public class ModuleEntity extends BaseEntity {
    private String name;
    private String description;

    @JoinColumn
    @ManyToOne
    private CourseEntity course;

    private int orderNum;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "module")
    private List<LessonEntity> lessonEntities;
}
