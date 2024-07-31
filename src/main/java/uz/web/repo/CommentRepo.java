package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.CommentEntity;

import java.util.List;
import java.util.UUID;

@Repository
public class CommentRepo extends BaseRepo<CommentEntity>{
    public CommentRepo() {
        super.aClass = CommentEntity.class;
    }

    public List<CommentEntity> findByComment(UUID commentId){
        return entityManager
                .createQuery("from CommentEntity c where c.id = :commentId", CommentEntity.class)
                .setParameter("commentId", commentId)
                .getResultList();

    }

    public List<CommentEntity> getComments(UUID courseId) {
        return entityManager.createQuery("from CommentEntity c where c.course.id = :course", CommentEntity.class)
                .setParameter("course", courseId).getResultList();
    }
}





