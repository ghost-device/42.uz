package uz.web.domain.DTO;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDTO {
    private UUID userId;
    private UUID courseId;
    private int rating;
}
