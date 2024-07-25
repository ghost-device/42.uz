package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.web.domain.DAO.CourseDAO;
import uz.web.domain.DTO.CourseDTO;
import uz.web.domain.entity.CourseEntity;
import uz.web.domain.entity.CoursesOfUsersEntity;
import uz.web.repo.CourseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CourseService extends BaseService<CourseEntity> {
    private final CourseRepo courseRepo;
    private final CloudService cloudService;
    private final UserService userService;
    private final MentorService mentorService;

    public List<CourseDAO> getAllCourse(boolean isActive) {
        return getCourseDAOS(courseRepo.getAllCourse(isActive));
    }

    public List<CourseDAO> getCoursesOfUser(UUID userId) {
        return getCourseDAOS(
                userService.findById(userId)
                        .getCoursesOfUsersEntities()
                        .stream()
                        .map(CoursesOfUsersEntity::getCourse)
                        .filter(CourseEntity::isActive)
                        .collect(Collectors.toList())
        );
    }

    private List<CourseDAO> getCourseDAOS(List<CourseEntity> courseEntities) {
        List<CourseDAO> list = new ArrayList<>();

        for (CourseEntity course : courseEntities) {
            list.add(new CourseDAO(
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
    public void saveCourse(CourseDTO courseDTO) {
        this.save(
                CourseEntity.builder()
                        .name(courseDTO.getName())
                        .description(courseDTO.getDescription())
                        .price(courseDTO.getPrice())
                        .imageId(
                                cloudService.uploadFile(courseDTO.getFaceImgOfCourse())
                        )
                        .mentor(mentorService.findById(courseDTO.getMentorId()))
                        .build()
        );
    }

    @Override
    public void save(CourseEntity courseEntity) {
        courseRepo.save(courseEntity);
    }

    @Override
    public CourseEntity findById(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(CourseEntity courseEntity) {

    }
}