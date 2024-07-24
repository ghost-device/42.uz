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
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(unique = true)
    private String email;

    private String password;
    private Integer balance;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<PaymentEntity> paymentEntities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<RatingEntity> ratingEntities;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<CommentEntity> commentEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CoursesOfUsersEntity> coursesOfUsersEntities;
}
