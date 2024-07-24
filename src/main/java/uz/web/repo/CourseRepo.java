package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.CourseEntity;

import java.util.List;
import java.util.UUID;

@Repository
public class CourseRepo extends BaseRepo<CourseEntity> {
    public List<CourseEntity> getAllCourse() {
        return entityManager.createQuery("from CourseEntity c", CourseEntity.class).getResultList();
    }
    
    public List<CourseEntity> getCoursesByUser(UUID id) {
        return entityManager
                .createQuery("select u.course from CoursesOfUsersEntity u where u.user.id = :id", CourseEntity.class)
                .setParameter("id", id)
                .getResultList();
    }
}
