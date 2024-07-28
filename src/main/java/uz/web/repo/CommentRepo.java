package uz.web.repo;

import jakarta.persistence.TypedQuery;
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


    public List<CommentEntity> getAllComments(Integer pageNumber, Integer pageSize){
       return entityManager.createQuery("from CommentEntity", CommentEntity.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }





}





