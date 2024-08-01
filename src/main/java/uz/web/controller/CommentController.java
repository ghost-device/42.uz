package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.DTO.CommentDTO;
import uz.web.domain.entity.CommentEntity;
import uz.web.domain.entity.CourseEntity;
import uz.web.service.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CourseService courseService;
    private final ModuleService moduleService;
    private final RatingService ratingService;
    private final UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addComment(@ModelAttribute CommentDTO commentDTO, HttpSession session, Model model){
        try {
            commentService.saveComment(commentDTO, ((UserDao) session.getAttribute("user")).getId());
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("comments", commentService.getCommentOfCourse(commentDTO.getCourseId()));
        model.addAttribute("rating", ratingService.avgRate(commentDTO.getCourseId()));
        model.addAttribute("isBuy", true);
        model.addAttribute("modules", moduleService.getModulesOfCourse(commentDTO.getCourseId()));
        model.addAttribute("course", courseService.getCourseDAOS(List.of(courseService.findById(commentDTO.getCourseId()))).get(0));
        model.addAttribute("balance", userService.findById(((UserDao) session.getAttribute("user")).getId()).getBalance());
        return "user-modules";
    }

    @RequestMapping("/all/{courseId}")
    public String allComments(@PathVariable("courseId") UUID courseId, Model model){
        model.addAttribute("comments", courseService.findById(courseId).getCommentEntities());
        return "admin-comments-control";
    }

    @RequestMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") UUID commentId, Model model){
        try {
            commentService.delete(commentId);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("comments", courseService.findById(commentId).getCommentEntities());
        return "admin-comments-control";
    }
}
