package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.DTO.RatingDTO;
import uz.web.service.CommentService;
import uz.web.service.CourseService;
import uz.web.service.ModuleService;
import uz.web.service.RatingService;

import java.util.List;

@Controller
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    private final CommentService commentService;
    private final CourseService courseService;
    private final ModuleService moduleService;

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public String rate(@ModelAttribute RatingDTO ratingDTO, HttpSession httpSession, Model model){
        try {
            ratingService.rateCourse(ratingDTO, ((UserDao) httpSession.getAttribute("user")).getId());
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("rating", ratingService.avgRate(ratingDTO.getCourseId()));
        model.addAttribute("isBuy", true);
        model.addAttribute("modules", moduleService.getModulesOfCourse(ratingDTO.getCourseId()));
        model.addAttribute("course", courseService.getCourseDAOS(List.of(courseService.findById(ratingDTO.getCourseId()))).get(0));
        model.addAttribute("comment", commentService.getCommentOfCourse(ratingDTO.getCourseId()));
        return "user-modules";
    }
}
