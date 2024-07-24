package uz.web.service;

import uz.web.domain.entity.MentorEntity;
import uz.web.repo.MentorRepo;

public class MentorService extends BaseService<MentorEntity, MentorRepo>{

    public Integer mentorNumber(){
        return repo.mentorNumber();
    }
}
