package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.MentorDAO;
import uz.web.domain.entity.CourseEntity;
import uz.web.domain.entity.MentorEntity;
import uz.web.repo.MentorRepo;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MentorService extends BaseService<MentorEntity> {
    private final MentorRepo mentorRepo;

    public List<MentorDAO> getAllMentors() {
        return mentorRepo.getAllMentors();
    }

    @Override
    public void save(MentorEntity mentorEntity) {
        mentorRepo.save(mentorEntity);
    }

    @Override
    public MentorEntity findById(UUID id) {
        return mentorRepo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        mentorRepo.delete(id);
    }

    @Override
    public void update(MentorEntity mentorEntity) {
        mentorRepo.update(mentorEntity);
    }
}
