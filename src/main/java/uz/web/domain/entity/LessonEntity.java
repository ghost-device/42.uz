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
