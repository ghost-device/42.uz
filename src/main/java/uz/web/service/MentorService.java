package uz.web.service;

import org.springframework.stereotype.Service;
import uz.web.domain.entity.MentorEntity;

import java.util.UUID;

@Service
public class MentorService extends BaseService<MentorEntity> {
    @Override
    public void save(MentorEntity mentorEntity) {

    }

    @Override
    public MentorEntity findById(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(MentorEntity mentorEntity) {

    }
}
