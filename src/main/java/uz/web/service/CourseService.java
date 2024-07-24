package uz.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.CourseDAO;
import uz.web.domain.entity.CourseEntity;
import uz.web.domain.entity.CoursesOfUsersEntity;
import uz.web.repo.CourseRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CourseService implements BaseService<CourseEntity> {
    private final CourseRepo repo;
    private final CloudService cloudService;
    private final UserService userService;

    public List<CourseDAO> getAllCourse() {
        return getCourseDAOS(repo.getAllCourse());
    }

    public List<CourseDAO> getCoursesByUser(UUID userId) {
        return getCourseDAOS(
                userService.findById(userId)
                        .getCoursesOfUsersEntities()
                        .stream()
                        .map(CoursesOfUsersEntity::getCourse)
                        .toList()
        );
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

    @Override
    public void save(CourseEntity courseEntity) {

    }

    @Override
    public CourseEntity findById(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(CourseEntity courseEntity) {

    }
}