package uz.web.service;

import uz.web.domain.entity.CourseEntity;
import uz.web.repo.CourseRepo;

public class CourseService extends BaseService<CourseEntity, CourseRepo>{

    public Integer coursesNumber(){
        return repo.coursesNumber();
    }

}


