package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uz.web.domain.DAO.LessonForModuleDAO;
import uz.web.domain.DTO.ModuleDTO;
import uz.web.domain.DTO.ModuleUpdateDTO;
import uz.web.service.LessonService;
import uz.web.service.ModuleService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;
    private final LessonService lessonService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createModule(@ModelAttribute ModuleDTO moduleDTO, Model model) {
        try {
            moduleService.saveModule(moduleDTO);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("modules", moduleService.getModulesOfCourse(moduleDTO.getCourseId()));
        return "admin-modules-control";
    }

    @RequestMapping("/lessons")
    public String moduleLessons(@RequestParam("moduleId") UUID moduleId, Model model, HttpSession session) {
        try {
            List<LessonForModuleDAO> lessons = lessonService.getLessonsOfModule(moduleId);
            model.addAttribute("lessons", lessons);
        } catch (Exception e) {
            session.setAttribute("moduleId", moduleId);
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "admin-lesson-control";
    }

    @RequestMapping("/delete")
    public String deleteModule(@RequestParam("moduleId") UUID moduleId, Model model) {
        try {
            moduleService.deleteModule(moduleId);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("modules", moduleService.getModulesOfCourse(moduleId));
        return "admin-modules-control";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateModule(@ModelAttribute ModuleUpdateDTO moduleUpdate, Model model, HttpSession session) {
        try {
            moduleService.update(moduleUpdate);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("modules", moduleService.findById(moduleUpdate.getId())
                .getCourse().getModuleEntities());

        return "admin-modules-control";
    }
}
