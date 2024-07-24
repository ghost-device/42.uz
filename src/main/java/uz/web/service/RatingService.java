package uz.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.web.domain.DTO.RatingDTO;
import uz.web.domain.entity.RatingEntity;
import uz.web.repo.RatingRepo;

@Service
public class RatingService extends BaseService<RatingEntity, RatingRepo> {
    private UserService userService;
    private CourseService courseService;

    @Transactional
    public void rateCourse(RatingDTO rating) {
        repo.save(RatingEntity.builder()
                .user(userService.findById(rating.getUserId()))
                .rating(rating.getRating())
                .course(courseService.findById(rating.getCourseId()))
                .build());


    }

}
