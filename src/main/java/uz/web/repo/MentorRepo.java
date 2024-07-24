package uz.web.repo;

import uz.web.domain.entity.MentorEntity;

public class MentorRepo extends BaseRepo<MentorEntity>{

    public MentorRepo() {
        super.aClass = MentorEntity.class;
    }


    public Integer mentorNumber(){
        return entityManager.createQuery("select count(*) from MentorEntity ", Integer.class).getSingleResult();
    }


}


