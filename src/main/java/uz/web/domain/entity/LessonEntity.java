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

    private String description;

    private int orderNum;

    private String videoId;

    private Integer videoDuration;
}
