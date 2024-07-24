package uz.web.service;

import uz.web.domain.DAO.CourseDAO;
import uz.web.domain.entity.CourseEntity;
import uz.web.repo.CourseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseService extends BaseService<CourseEntity, CourseRepo> {
    private CloudService cloudService;

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
}