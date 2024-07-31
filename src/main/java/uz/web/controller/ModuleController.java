package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.web.domain.DAO.LessonForModuleDAO;
import uz.web.domain.DAO.ModuleDAO;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.DTO.ModuleDTO;
import uz.web.domain.DTO.ModuleUpdateDTO;
import uz.web.domain.entity.CourseEntity;
import uz.web.service.LessonService;
import uz.web.service.ModuleService;
import uz.web.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;
    private final LessonService lessonService;
    private final UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createModule(@ModelAttribute ModuleDTO moduleDTO, Model model) {
        try {
            moduleService.saveModule(moduleDTO);
            model.addAttribute("courseId", moduleDTO.getCourseId());
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("courseId", moduleDTO.getCourseId());
        model.addAttribute("modules", moduleService.getModulesOfCourse(moduleDTO.getCourseId()));
        return "admin-modules-control";
    }

    @RequestMapping("/lessons/{moduleId}")
    public String moduleLessons(@PathVariable("moduleId") UUID moduleId, Model model) {
        try {
            List<LessonForModuleDAO> lessons = lessonService.getLessonsOfModule(moduleId);
            model.addAttribute("lessons", lessons);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("moduleId", moduleId);
        return "admin-lesson-control";
    }

    @RequestMapping("/u-lessons/{moduleId}")
    public String lessonsOfModule(@PathVariable("moduleId") UUID moduleId, Model model, HttpSession session) {
        try{
            model.addAttribute("lessons", lessonService.getLessonsOfModule(moduleId));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("balance", userService.findById(((UserDao) session.getAttribute("user")).getId()).getBalance());
        return "user-lessons";
    }

    @RequestMapping("/delete/{moduleId}")
    public String deleteModule(@PathVariable("moduleId") UUID moduleId, Model model) {
        CourseEntity course = moduleService.findById(moduleId).getCourse();
        try {
            moduleService.deleteModule(moduleId);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        List<ModuleDAO> modules = moduleService.getModulesOfCourse(course.getId());
        model.addAttribute("courseId", course.getId());
        model.addAttribute("modules", modules);
        return "admin-modules-control";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateModule(@ModelAttribute ModuleUpdateDTO moduleUpdate, Model model) {
        try {
            moduleService.update(moduleUpdate);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("courseId", moduleService.findById(moduleUpdate.getId()).getCourse().getId());
        model.addAttribute("modules", moduleService.getModulesOfCourse(moduleService.
                findById(moduleUpdate.getId()).getCourse().getId()));

        return "admin-modules-control";
    }
}
