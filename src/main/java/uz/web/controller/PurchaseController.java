package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.domain.DAO.UserDao;
import uz.web.service.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final CourseOfUsersService courseOfUsersService;
    private final CourseService courseService;
    private final ModuleService moduleService;
    private final RatingService ratingService;
    private final CommentService commentService;
    private final UserService userService;

    @RequestMapping(value = "/{courseId}")
    public String purchaseToCourse(@PathVariable("courseId") UUID courseId, HttpSession session, Model model){
        try {
            courseOfUsersService.purchaseCourse(courseId, ((UserDao) session.getAttribute("user")).getId());
            model.addAttribute("isBuy", true);
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("comment", commentService.getCommentOfCourse(courseId));
        model.addAttribute("rating", ratingService.avgRate(courseId));
        model.addAttribute("modules", moduleService.getModulesOfCourse(courseId));
        model.addAttribute("course", courseService.getCourseDAOS(List.of(courseService.findById(courseId))).get(0));
        model.addAttribute("balance", userService.findById(((UserDao) session.getAttribute("user")).getId()).getBalance());
        return "user-modules";
    }
}
