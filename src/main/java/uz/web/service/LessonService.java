package uz.web.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.CourseDAO;
import uz.web.domain.DAO.LessonForModuleDAO;
import uz.web.domain.DAO.LessonWithVideoDAO;
import uz.web.domain.DTO.LessonDTO;
import uz.web.domain.entity.CourseEntity;
import uz.web.domain.entity.CoursesOfUsersEntity;
import uz.web.domain.entity.LessonEntity;
import uz.web.domain.exceptions.ThisCourseIsNotPurchasedException;
import uz.web.repo.LessonRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService extends BaseService<LessonEntity> {
    private final LessonRepo lessonRepo;
    private final CloudService cloudService;
    private final ModuleService moduleService;
    private final CourseOfUsersService courseOfUsersService;

    public List<LessonForModuleDAO> getLessonsOfModule(UUID moduleId){
        List<LessonForModuleDAO> list = new ArrayList<>();

        for (LessonEntity lesson : moduleService.findById(moduleId).getLessonEntities()) {
            list.add(new LessonForModuleDAO(lesson.getId(), lesson.getName(), lesson.getOrderNum(), lesson.getVideoDuration()));
        }
        return list;
    }

    public LessonWithVideoDAO getLessonWithVideo(UUID lessonId, UUID userId){
        LessonEntity lesson = this.findById(lessonId);

        UUID courseId = lesson.getModule().getCourse().getId();

        courseOfUsersService.checkPurchase(userId, courseId);

        return new LessonWithVideoDAO(lesson.getName(), cloudService.getFileUrl(lesson.getVideoId()));
    }

    @Transactional
    public void saveLesson(LessonDTO lessonDTO){
        int orderNum = lessonDTO.getOrderNum();

        List<LessonEntity> lessonEntities = moduleService.findById(lessonDTO.getModuleId()).getLessonEntities();

        for (LessonEntity lesson : lessonEntities) {
            if (lesson.getOrderNum() >= orderNum){
                lesson.setOrderNum(lesson.getOrderNum() + 1);
                this.update(lesson);
            }
        }

        this.save(LessonEntity.builder()
                        .name(lessonDTO.getName())
                        .module(moduleService.findById(lessonDTO.getModuleId()))
                        .videoId(cloudService.uploadFile(lessonDTO.getVideoOfLesson()))
                        .videoDuration(lessonDTO.getVideoDuration())
                        .orderNum(lessonDTO.getOrderNum())
                        .build()
        );
    }

    @Override
    public void save(LessonEntity lessonEntity) {
        lessonRepo.save(lessonEntity);
    }

    @Override
    public LessonEntity findById(UUID id) {
        return lessonRepo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        lessonRepo.delete(id);
    }

    @Override
    public void update(LessonEntity lessonEntity) {
        lessonRepo.update(lessonEntity);
    }
}
