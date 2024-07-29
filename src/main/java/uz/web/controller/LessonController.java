package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DAO.LessonForModuleDAO;
import uz.web.domain.DTO.AddLessonDTO;
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
    public String addLesson(@ModelAttribute AddLessonDTO addLesson,
                            @RequestParam("videoFile") MultipartFile video,
                            Model model) {
        try {
            lessonService.save(addLesson, video);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "admin-lesson-control";
    }
}
