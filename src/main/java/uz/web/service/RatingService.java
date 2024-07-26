package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.web.domain.DTO.RatingDTO;
import uz.web.domain.entity.CourseEntity;
import uz.web.domain.entity.CoursesOfUsersEntity;
import uz.web.domain.entity.RatingEntity;
import uz.web.domain.entity.UserEntity;
import uz.web.domain.exceptions.CourseNotFoundException;
import uz.web.domain.exceptions.UserNotFoundException;
import uz.web.repo.RatingRepo;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RatingService extends BaseService<RatingEntity> {
    private final RatingRepo ratingRepo;
    private final UserService userService;
    private final CourseService courseService;

    @Transactional
    public void rateCourse(RatingDTO rating) {
        UserEntity user = userService.findById(rating.getUserId());
        CourseEntity course = courseService.findById(rating.getCourseId());
        if (user == null) {
            throw new UserNotFoundException("User not found!");
        }
        if (course == null) {
            throw new CourseNotFoundException("Course not found!");
        }

        List<CoursesOfUsersEntity> userCourses = user.getCoursesOfUsersEntities();

        for (CoursesOfUsersEntity courses : userCourses) {
            if (courses.getCourse().getId().equals(course.getId())) {
                List<RatingEntity> ratings = course.getRatingEntities();
                ratings.add(RatingEntity.builder()
                        .course(courses.getCourse())
                        .rating(rating.getRating())
                        .user(user)
                        .build());
                course.setRatingEntities(ratings);
                courseService.update(course);
                break;
            }
        }

        this.save(RatingEntity.builder()
                .user(userService.findById(rating.getUserId()))
                .rating(rating.getRating())
                .course(courseService.findById(rating.getCourseId()))
                .build());
    }

    public Double avgRate(UUID courseId) {
        CourseEntity course = courseService.findById(courseId);
        List<RatingEntity> ratings = course.getRatingEntities();
        double ratingSum = 0;
        for (RatingEntity rating : ratings) {
            ratingSum += rating.getRating();
        }
        return ratingSum / ratings.size();
    }

    @Override
    public void save(RatingEntity ratingEntity) {
        ratingRepo.save(ratingEntity);
    }

    @Override
    public RatingEntity findById(UUID id) {
        return ratingRepo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        ratingRepo.delete(id);
    }

    @Override
    public void update(RatingEntity ratingEntity) {
        ratingRepo.update(ratingEntity);
    }
}
