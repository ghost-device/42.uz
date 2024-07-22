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
