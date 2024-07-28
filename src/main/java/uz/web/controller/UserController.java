package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.entity.UserEntity;
import uz.web.service.CourseService;
import uz.web.service.ModuleService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final CourseService courseService;
    private final ModuleService moduleService;

    @RequestMapping("/main-menu")
    public String throwToMainMenu(HttpSession session, Model model){
        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("userCourses", courseService.getCoursesByUser(((UserDao) session.getAttribute("user")).getId()));
        return "user-main-menu";
    }
}
