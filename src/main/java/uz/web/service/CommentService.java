package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.web.domain.DAO.CommentDAO;
import uz.web.domain.DTO.CommentDTO;
import uz.web.domain.entity.CommentEntity;
import uz.web.domain.entity.CourseEntity;
import uz.web.repo.CommentRepo;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentService extends BaseService<CommentEntity> {
    private final CommentRepo commentRepo;
    private final UserService userService;
    private final CourseService courseService;

    @Transactional
    public void saveComment(CommentDTO commentDTO, UUID userId) {
        this.save(new CommentEntity(
                userService.findById(userId),
                courseService.findById(commentDTO.getCourseId()),
                commentDTO.getComment()
        ));
    }

    public List<CommentDAO> getCommentOfCourse(UUID courseId) {
        CourseEntity course = courseService.findById(courseId);

        return course.getCommentEntities()
                .stream()
                .sorted(Comparator.comparing(CommentEntity::getCreatedAt).reversed())
                .map((c) -> new CommentDAO(c.getUser().getEmail(), c.getComment()))
                .toList();
    }

    @Override
    public void save(CommentEntity commentEntity) {
        commentRepo.save(commentEntity);
    }

    @Override
    public CommentEntity findById(UUID id) {
        return commentRepo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        commentRepo.delete(id);
    }

    @Override
    public void update(CommentEntity commentEntity) {
        commentRepo.update(commentEntity);
    }
}
