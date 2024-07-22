package uz.web.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.web.domain.enumerators.CodeStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "codes")
public class CodeEntity extends BaseEntity {
    @JoinColumn
    @OneToOne
    private UserEntity user;

    private String code;

    private CodeStatus status;
}
