package uz.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.web.domain.DAO.CourseDAO;
import uz.web.domain.entity.CourseEntity;
import uz.web.domain.entity.UserEntity;
import uz.web.domain.exceptions.CourseNotFoundException;
import uz.web.domain.exceptions.InvalidBalanceException;
import uz.web.domain.exceptions.UserNotFoundException;
import uz.web.repo.CourseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService extends BaseService<CourseEntity, CourseRepo> {
    private CloudService cloudService;
    private UserService userService;

    public List<CourseDAO> getAllCourse() {
        return getCourseDAOS(repo.getAllCourse());
    }

    public List<CourseDAO> getCoursesByUser(UUID id) {
        return getCourseDAOS(repo.getCoursesByUser(id));
    }

    private List<CourseDAO> getCourseDAOS(List<CourseEntity> courseEntities) {
        List<CourseDAO> list = new ArrayList<>();

        for (CourseEntity course : courseEntities) {
            list.add(new CourseDAO(
                    course.getName(),
                    course.getDescription(),
                    course.getMentor().getName(),
                    course.getPrice(),
                    cloudService.getFileUrl(course.getImageId())
            ));
        }

        return list;
    }

    @Transactional
    public void purchaseCourse(UUID courseId, UUID userId) {
        CourseEntity course = repo.findById(courseId);
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
        List<CourseEntity> userCourses = user.getCourseEntities();
        userCourses.add(course);
        user.setCourseEntities(userCourses);
        userService.update(user);

    }
}