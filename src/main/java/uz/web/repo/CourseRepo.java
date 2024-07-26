package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.CourseEntity;

import java.util.List;
import java.util.UUID;

@Repository
public class CourseRepo extends BaseRepo<CourseEntity> {
    public CourseRepo() {
        super.aClass = CourseEntity.class;
    }

    public List<CourseEntity> getAllCourse(boolean isActive) {
        return entityManager
                .createQuery("from CourseEntity c" + (isActive ? " where c.isActive" : ""), CourseEntity.class)
                .getResultList();
    }


    public List<CourseEntity> getCoursesByMentorId(UUID mentorId) {
        return entityManager.createQuery(" select c from CourseEntity  c where c.mentor.id = :mentorId ", CourseEntity.class)
                .setParameter("mentorId", mentorId)
                .getResultList();
    }


    public CourseEntity findByCourseId(UUID courseId){
        return entityManager.find(CourseEntity.class, courseId);
    }


}
