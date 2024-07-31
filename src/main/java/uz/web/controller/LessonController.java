package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DAO.LessonWithVideoDAO;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.DTO.AddLessonUpdDTO;
import uz.web.domain.DTO.LessonDTO;
import uz.web.domain.entity.LessonEntity;
import uz.web.domain.entity.ModuleEntity;
import uz.web.service.LessonService;
import uz.web.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final UserService userService;

    @RequestMapping("/{lessonId}")
    public String getVideo(@PathVariable("lessonId") UUID lessonId, HttpSession session, Model model) {
        try {
            LessonWithVideoDAO lessonWithVideo = lessonService.getLessonWithVideo(lessonId, ((UserDao) session.getAttribute("user")).getId());
            model.addAttribute("lesson", lessonWithVideo);
            return "video";
        } catch (Exception e) {
            model.addAttribute("lessons", lessonService.getLessonsOfModule(lessonService.findById(lessonId).getModule().getId()));
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("balance", userService.findById(((UserDao) session.getAttribute("user")).getId()).getBalance());
            return "user-lessons";
        }
    }

    @RequestMapping("/add")
    public String addLesson(@ModelAttribute LessonDTO lessonDTO,
                            @RequestParam("videoFile") MultipartFile video,
                            Model model) {
        try {
            lessonService.saveLesson(lessonDTO, video);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("moduleId", lessonDTO.getId());
        model.addAttribute("lessons", lessonService.getLessonsOfModule(lessonDTO.getId()));

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
