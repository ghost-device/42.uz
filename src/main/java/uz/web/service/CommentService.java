package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.entity.CommentEntity;
import uz.web.domain.entity.CourseEntity;
import uz.web.repo.CommentRepo;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentService extends BaseService<CommentEntity>{

    private final CommentRepo commentRepo;
    private final CourseService courseService;


    public void deleteComment(CommentEntity commentEntity){
        List<CommentEntity> byComment = commentRepo.findByComment(commentEntity.getId());
        CourseEntity course = courseService.findByCourseId(commentEntity.getCourse().getId());

        byComment.removeIf(commentEntity1 ->
                commentEntity1.getCourse().getId().equals(commentEntity.getCourse().getId()) &&
                commentEntity1.getUser().getId().equals(commentEntity.getUser().getId()));

        course.setCommentEntities(byComment);

        courseService.update(course);
    }


    @Override
    public void save(CommentEntity commentEntity) {
    }

    @Override
    public CommentEntity findById(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(CommentEntity commentEntity) {

    }
}
