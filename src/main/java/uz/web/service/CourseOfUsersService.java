package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.web.domain.entity.CourseEntity;
import uz.web.domain.entity.CoursesOfUsersEntity;
import uz.web.domain.entity.UserEntity;
import uz.web.domain.exceptions.CourseNotFoundException;
import uz.web.domain.exceptions.InvalidBalanceException;
import uz.web.domain.exceptions.ThisCourseIsNotPurchasedException;
import uz.web.domain.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CourseOfUsersService extends BaseService<CoursesOfUsersEntity> {
    private final CourseService courseService;
    private final UserService userService;

    @Transactional
    public void purchaseCourse(UUID courseId, UUID userId) {
        CourseEntity course = courseService.findById(courseId);
        UserEntity user = userService.findById(userId);

        if (course == null) {
            throw new CourseNotFoundException("Course not found!");
        }

        if (user == null) {
            throw new UserNotFoundException("User not found!");
        }

        if (user.getBalance() < course.getPrice()) {
            throw new InvalidBalanceException("Balance is not enough!");
        }

        List<CoursesOfUsersEntity> userCourses = user.getCoursesOfUsersEntities();
        userCourses.add(new CoursesOfUsersEntity(user, course));
        user.setCoursesOfUsersEntities(userCourses);
        userService.update(user);
    }

    public void checkPurchase(UUID userId, UUID courseId) {
        Optional<CourseEntity> list = userService.findById(userId).getCoursesOfUsersEntities().stream().map(CoursesOfUsersEntity::getCourse).filter((c) -> c.getId().equals(courseId)).findFirst();
        list.orElseThrow(() -> new ThisCourseIsNotPurchasedException("You are not purchase for this course!"));
    }

    @Override
    public void save(CoursesOfUsersEntity coursesOfUsersEntity) {

    }

    @Override
    public CoursesOfUsersEntity findById(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(CoursesOfUsersEntity coursesOfUsersEntity) {

    }
}
