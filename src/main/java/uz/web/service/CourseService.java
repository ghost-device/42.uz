package uz.web.service;

import uz.web.domain.DAO.CourseDAO;
import uz.web.domain.entity.CourseEntity;
import uz.web.repo.CourseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseService extends BaseService<CourseEntity, CourseRepo> {


//    public List<CourseDAO> getAllCourse() {
//        List<CourseDAO> list = new ArrayList<>();
//
//        for (CourseEntity course : repo.getAllCourse()) {
//            list.add(new CourseDAO(
//                    course.getName(),
//                    course.getDescription(),
//                    course.getMentor().getName(),
//                    course.getPrice(),
//
//            ));
//        }
//    }

    public List<CourseEntity> getCoursesByUser(UUID id) {
        return repo.getCoursesByUser(id);
    }
}