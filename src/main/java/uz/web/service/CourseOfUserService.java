package uz.web.service;

import uz.web.domain.entity.CoursesOfUsersEntity;
import uz.web.repo.CourseOfUserRepo;

public class CourseOfUserService extends BaseService<CoursesOfUsersEntity, CourseOfUserRepo>{


    public Integer userCourses(){
        return repo.userCourses();
    }


}


