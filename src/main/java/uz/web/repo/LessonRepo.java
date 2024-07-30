package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.LessonEntity;

@Repository
public class LessonRepo extends BaseRepo<LessonEntity> {
    public LessonRepo() {super.aClass = LessonEntity.class;}
}
