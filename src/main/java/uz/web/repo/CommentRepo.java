package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.CommentEntity;

@Repository
public class CommentRepo extends BaseRepo<CommentEntity>{
    public CommentRepo() {
        super.aClass = CommentEntity.class;
    }

}





