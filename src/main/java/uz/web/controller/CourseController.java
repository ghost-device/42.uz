package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.DTO.CourseDTO;
import uz.web.domain.DTO.CourseUpdateDTO;
import uz.web.service.CourseOfUsersService;
import uz.web.service.CourseService;
import uz.web.service.MentorService;
import uz.web.service.ModuleService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final MentorService mentorService;
    private final CourseService courseService;
    private final ModuleService moduleService;
    private final CourseOfUsersService courseOfUsersService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String adminCoursesControl(@ModelAttribute CourseDTO courseDTO,
                                      @RequestParam("img") MultipartFile multipartFile,
                                          Model model) {
        try {
            courseService.save(courseDTO, multipartFile);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("mentors", mentorService.getAllMentors());

        return "admin-dashboard";
    }

    @RequestMapping()
    public String throwToCoursesPageOfUser(Model model){
        model.addAttribute("courses", courseService.getAllCourse());
        return "user-courses-page";
    }

    @RequestMapping("/modules/{courseId}")
    public String adminModulesControl(@PathVariable("courseId") UUID courseId, Model model, HttpSession session) {
        model.addAttribute("modules", moduleService.getModulesOfCourse(courseId));
        model.addAttribute("courseId", courseId);
        return "admin-modules-control";
    }

    @RequestMapping(value = "/delete/{courseId}")
    public String deleteCourse(@PathVariable("courseId") UUID courseId, Model model){
        try {
            courseService.delete(courseId);
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("mentors", mentorService.getAllMentors());
        return "admin-dashboard";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCourse(@ModelAttribute CourseUpdateDTO courseDTO,
                              @RequestParam("img") MultipartFile multipartFile,
                                      Model model) {
        try {
            courseService.update(courseDTO, multipartFile);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("courses", courseService.getAllCourse());
        model.addAttribute("mentors", mentorService.getAllMentors());

        return "admin-dashboard";
    }

    @RequestMapping("/u-modules/{courseId}")
    public String throwToCourseModules(@PathVariable("courseId") UUID courseId, Model model, HttpSession session){
        model.addAttribute("modules", moduleService.getModulesOfCourse(courseId));
        model.addAttribute("course", courseService.getCourseDAOS(List.of(courseService.findById(courseId))).get(0));

        try {
            courseOfUsersService.checkPurchase(((UserDao) session.getAttribute("user")).getId(), courseId);
            model.addAttribute("isBuy", true);
        } catch (Exception e){
            model.addAttribute("isBuy", false);
        }

        return "user-modules";
    }
}
