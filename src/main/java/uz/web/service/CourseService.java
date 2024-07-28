package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DAO.CourseDAO;
import uz.web.domain.DTO.CourseDTO;
import uz.web.domain.DTO.CourseUpdateDTO;
import uz.web.domain.entity.CourseEntity;
import uz.web.domain.entity.CoursesOfUsersEntity;
import uz.web.repo.CourseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CourseService extends BaseService<CourseEntity> {
    private final CourseRepo courseRepo;
    private final CloudService cloudService;
    private final UserService userService;
    private final MentorService mentorService;

    public List<CourseDAO> getAllCourse() {
        return getCourseDAOS(courseRepo.getAllCourse());
    }

    public List<CourseDAO> getCoursesByMentorId(UUID mentorId) {
        return getCourseDAOS(courseRepo.getCoursesByMentorId(mentorId));
    }

    public List<CourseDAO> getCoursesByUser(UUID userId) {
        return getCourseDAOS(
                userService.findById(userId)
                        .getCoursesOfUsersEntities()
                        .stream()
                        .map(CoursesOfUsersEntity::getCourse)
                        .toList()
        );
    }

    public CourseEntity findByCourseId(UUID courseId){
        return courseRepo.findByCourseId(courseId);
    }

    private List<CourseDAO> getCourseDAOS(List<CourseEntity> courseEntities) {
        List<CourseDAO> list = new ArrayList<>();

        for (CourseEntity course : courseEntities) {
            list.add(new CourseDAO(
                    course.getId(),
                    course.getName(),
                    course.getDescription(),
                    course.getMentor().getName(),
                    course.getPrice(),
                    cloudService.getFileUrl(course.getImageId())
            ));
        }

        return list;
    }

    @Transactional
    public void save(CourseDTO courseDTO, MultipartFile file) {
        CourseEntity courseEntity = CourseEntity.builder()
                .name(courseDTO.getName())
                .description(courseDTO.getDescription())
                .price(courseDTO.getPrice())
                .imageId(cloudService.uploadFile(file))
                .mentor(mentorService.findById(courseDTO.getMentorId()))
                .build();

        save(courseEntity);
    }

    @Transactional
    public void update(CourseUpdateDTO courseDTO, MultipartFile multipartFile){
        CourseEntity course = this.findById(courseDTO.getId());

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setImageId(cloudService.uploadFile(multipartFile));
        course.setMentor(mentorService.findById(courseDTO.getMentorId()));

        this.update(course);
    }

    @Override
    public void save(CourseEntity courseEntity) {
        courseRepo.save(courseEntity);
    }

    @Override
    public CourseEntity findById(UUID id) {
        return courseRepo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        courseRepo.delete(id);
    }

    @Override
    public void update(CourseEntity courseEntity) {
        courseRepo.update(courseEntity);
    }
}