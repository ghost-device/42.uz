package uz.web.repo;

import uz.web.domain.entity.CoursesOfUsersEntity;

public class CourseOfUserRepo extends BaseRepo<CoursesOfUsersEntity>{

    public CourseOfUserRepo() {
        super.aClass = CoursesOfUsersEntity.class;
    }

    public Integer userCourses(){
        return entityManager.createQuery("select count(*) from CoursesOfUsersEntity ", Integer.class).getSingleResult();
    }




}



