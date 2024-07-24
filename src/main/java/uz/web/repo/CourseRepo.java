package uz.web.repo;

import jakarta.persistence.TypedQuery;
import uz.web.domain.entity.CourseEntity;
import java.util.List;
import java.util.UUID;

public class CourseRepo extends BaseRepo<CourseEntity> {

    public List<CourseEntity> findAllCourse() {
        String findAll = "select c from CourseEntity c";
        TypedQuery<CourseEntity> query = entityManager.createQuery(findAll, CourseEntity.class);
        return query.getResultList();
    }

    public List<CourseEntity> findCoursesByUser(UUID id) {
        return entityManager.createQuery("select u.course from CoursesOfUsersEntity u where u.user.id = :id", CourseEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

}
