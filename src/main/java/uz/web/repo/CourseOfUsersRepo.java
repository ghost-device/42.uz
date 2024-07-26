package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.CoursesOfUsersEntity;

import java.util.List;

@Repository
public class CourseOfUsersRepo extends BaseRepo<CoursesOfUsersEntity> {

    public List<CoursesOfUsersEntity> getAllCoursesOfUsers(){
        return entityManager
                .createQuery("from CoursesOfUsersEntity", CoursesOfUsersEntity.class).
                getResultList();
    }


}


