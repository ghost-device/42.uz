package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DTO.CourseDTO;
import uz.web.domain.DTO.CreateCourseDTO;
import uz.web.service.CourseService;
import uz.web.service.MentorService;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final MentorService mentorService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String throwToCreateCoursePage(@ModelAttribute CreateCourseDTO createCourseDTO,
                                          @RequestParam("image") MultipartFile multipartFile,
                                          Model model) {
        try {
            courseService.saveCourse(new CourseDTO(createCourseDTO.getName(), createCourseDTO.getDescription(), createCourseDTO.getPrice(), multipartFile, createCourseDTO.getMentorId()));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("courses", courseService.getAllCourse(false));
        model.addAttribute("mentors", mentorService.getAllMentors());

        return "admin-dashboard";
    }

}
