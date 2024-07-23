package uz.web.repo;

import uz.web.domain.entity.CodeEntity;

public class CodeRepo extends BaseRepo<CodeEntity> {
    public CodeEntity getCodeByEmail(String email){
        return entityManager
                .createQuery("from CodeEntity c where c.email = :e", CodeEntity.class)
                .setParameter("e", email).getSingleResult();
    }
}
