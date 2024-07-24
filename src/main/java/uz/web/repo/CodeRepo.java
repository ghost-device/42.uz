package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.CodeEntity;

@Repository
public class CodeRepo extends BaseRepo<CodeEntity> {
    public CodeEntity getCodeByEmail(String email){
        return entityManager
                .createQuery("from CodeEntity c where c.email = :e", CodeEntity.class)
                .setParameter("e", email).getSingleResult();
    }
}
