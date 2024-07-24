package uz.web.repo;

import uz.web.domain.entity.CourseEntity;

public class CourseRepo extends BaseRepo<CourseEntity>{

    public CourseRepo() {
        super.aClass = CourseEntity.class;
    }


    public Integer coursesNumber(){
        return entityManager.createQuery("select count(*) from CourseEntity ", Integer.class).getSingleResult();
    }



}


