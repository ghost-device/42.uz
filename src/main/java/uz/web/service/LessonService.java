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
public class LessonService implements BaseService<LessonEntity> {
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
