package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DAO.MentorDAO;
import uz.web.domain.entity.MentorEntity;
import uz.web.repo.MentorRepo;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MentorService extends BaseService<MentorEntity> {
    private final MentorRepo mentorRepo;
    private final CloudService cloudService;

    public List<MentorDAO> getAllMentors() {

        List<MentorDAO> mentors = mentorRepo.getAllMentors();
        for (MentorDAO mentor : mentors) {
            mentor.setPictureUrl(cloudService.getFileUrl(mentor.getPictureUrl()));
        }
        return mentors;
    }

    @Transactional
    public void saveMentor(MentorEntity mentor, MultipartFile file) {
        mentor.setPictureId(cloudService.uploadFile(file));
        this.save(mentor);
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
