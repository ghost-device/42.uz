package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.LessonForModuleDAO;
import uz.web.domain.DAO.LessonWithVideoDAO;
import uz.web.domain.entity.LessonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService extends BaseService<LessonEntity> {
    private final CloudService cloudService;
    private final ModuleService moduleService;

    public List<LessonForModuleDAO> getLessonsOfModule(UUID moduleId){
        List<LessonForModuleDAO> list = new ArrayList<>();

        for (LessonEntity lesson : moduleService.findById(moduleId).getLessonEntities()) {
            list.add(new LessonForModuleDAO(lesson.getId(), lesson.getName(), lesson.getOrderNum(), lesson.getVideoDuration()));
        }

        return list;
    }

    public LessonWithVideoDAO getLessonWithVideo(UUID lessonId){
        LessonEntity lesson = this.findById(lessonId);
        return new LessonWithVideoDAO(lesson.getName(), cloudService.getFileUrl(lesson.getVideoId()));
    }

//    @Transactional
//    public void saveLesson(LessonDTO lessonDTO){
//        int orderNum = lessonDTO.getOrderNum();
//
//        List<LessonEntity> lessonEntities = moduleService.findById(lessonDTO.getModuleId()).getLessonEntities();
//
//        for (LessonEntity lesson : lessonEntities) {
//            if (lesson.getOrderNum() >= orderNum){
//                lesson.setOrderNum(lesson.getOrderNum() + 1);
//            }
//        }
//
//        this.save(LessonEntity.builder()
//                        .name(lessonDTO.getName())
//                        .module(moduleService.findById(lessonDTO.getModuleId()))
//                        .videoId(cloudService.uploadFile(lessonDTO.getVideoOfLesson()))
//                        .videoDuration()
//                        .build()
//        );
//    }

    @Override
    public void save(LessonEntity lessonEntity) {

    }

    @Override
    public LessonEntity findById(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(LessonEntity lessonEntity) {

    }
}
