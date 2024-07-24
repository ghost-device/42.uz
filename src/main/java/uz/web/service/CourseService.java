package uz.web.service;

import uz.web.domain.entity.CourseEntity;
import uz.web.repo.CourseRepo;

import java.util.List;
import java.util.UUID;

public class CourseService extends BaseService<CourseEntity, CourseRepo> {
    public List<CourseEntity> getAllCourse() {
        return repo.getAllCourse();
    }

    public List<CourseEntity> getCoursesByUser(UUID id) {
        return repo.getCoursesByUser(id);
    }
}