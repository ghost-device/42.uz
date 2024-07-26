package uz.web.domain.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MentorDAO {
    private String name;
    private UUID id;
    private String biography;
}
