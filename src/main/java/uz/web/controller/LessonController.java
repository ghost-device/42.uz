package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DTO.AddLessonUpdDTO;
import uz.web.domain.entity.LessonEntity;
import uz.web.domain.entity.ModuleEntity;
import uz.web.service.LessonService;
import uz.web.service.ModuleService;

import java.util.UUID;

@Controller
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final ModuleService moduleService;

    @RequestMapping("/add")
    public String addLesson(@ModelAttribute AddLessonUpdDTO addLesson,
                            @RequestParam("videoFile") MultipartFile video,
                            Model model) {
        try {
            lessonService.save(addLesson, video);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("moduleId", addLesson.getId());
        model.addAttribute("lessons", lessonService.getLessonsOfModule(addLesson.getId()));

        return "admin-lesson-control";
    }

    @RequestMapping("/delete/{lessonId}")
    public String deleteLesson(@PathVariable("lessonId") UUID lessonId, Model model) {
        LessonEntity lesson = lessonService.findById(lessonId);
        ModuleEntity module = lesson.getModule();
        try {
            lessonService.delete(lessonId);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("moduleId", module.getId());
        model.addAttribute("lessons", lessonService.getLessonsOfModule(module.getId()));
        return "admin-lesson-control";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateLesson(@ModelAttribute AddLessonUpdDTO updateLesson,
                               @RequestParam("videoFile") MultipartFile video,
                               Model model) {
        try {
            lessonService.updateLesson(updateLesson, video);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        UUID moduleId = lessonService.findById(updateLesson.getId()).getModule().getId();
        model.addAttribute("moduleId", moduleId);
        model.addAttribute("lessons", lessonService.getLessonsOfModule(moduleId));
        return "admin-lesson-control";
    }
}
