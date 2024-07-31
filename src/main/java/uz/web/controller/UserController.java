package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.web.domain.DAO.UserDao;
import uz.web.service.CourseService;
import uz.web.service.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CourseService courseService;

    @RequestMapping("/main-menu")
    public String userMainMenu(Model model, HttpSession session){
        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("userCourses", courseService.getCoursesByUser(((UserDao) session.getAttribute("user")).getId()));
        return "user-main-menu";
    }
}