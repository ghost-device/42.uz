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
@Table(name = "lessons")
public class LessonEntity extends BaseEntity {
    private String name;

    @JoinColumn
    @ManyToOne
    private ModuleEntity module;

    private int orderNum;

    private String videoId;

    private Integer videoDuration;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson")
    private List<LessonOfUserEntity> lessonOfUserEntities;
}
