package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.service.CourseService;
import uz.web.service.MentorService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CourseService courseService;
    private final MentorService mentorService;

    @RequestMapping(method = RequestMethod.GET)
    public String throwToAdminLogin() {
        return "admin-login";
    }
    @RequestMapping("/main")
    public String throwToAdminPage(Model model) {
        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("mentors", mentorService.getAllMentors());
        return "admin-dashboard";
    }
}
