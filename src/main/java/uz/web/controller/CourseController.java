package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DTO.CourseDTO;
import uz.web.domain.entity.UserEntity;
import uz.web.service.CourseService;
import uz.web.service.MentorService;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final MentorService mentorService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String adminCoursesControl(@ModelAttribute CourseDTO courseDTO,
                                          @RequestParam("img") MultipartFile multipartFile,
                                          Model model) {
        try {
            courseService.saveCourse(courseDTO, multipartFile);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("courses", courseService.getAllCourse(false));
        model.addAttribute("mentors", mentorService.getAllMentors());

        return "admin-dashboard";
    }

    @RequestMapping()
    public String throwToCoursesPageOfUser(Model model){
        model.addAttribute("courses", courseService.getAllCourse(false));
        return "user-courses-page";
    }
}
