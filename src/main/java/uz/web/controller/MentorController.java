package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DAO.MentorDAO;
import uz.web.domain.entity.MentorEntity;
import uz.web.service.MentorService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mentor")
@RequiredArgsConstructor
public class MentorController {
    private final MentorService mentorService;

    @RequestMapping()
    public String mentorPage(Model model) {
        model.addAttribute("mentors", mentorService.getAllMentors());
        return "admin-mentor-page";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addMentor(@ModelAttribute MentorEntity mentor,
                            @RequestParam("picture") MultipartFile file,
                            Model model) {
        try {
            mentorService.saveMentor(mentor, file);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        List<MentorDAO> allMentors = mentorService.getAllMentors();
        model.addAttribute("mentors", allMentors);
        return "admin-mentor-page";
    }

    @RequestMapping("/all")
    public String allMentors(Model model) {
        List<MentorDAO> allMentors = mentorService.getAllMentors();
        model.addAttribute("mentors", allMentors);
        return "admin-mentor-page";
    }

    @RequestMapping(value = "/delete/{mentorId}", method = RequestMethod.POST)
    public String deleteMentor(@PathVariable UUID mentorId, Model model) {
        try {
            mentorService.delete(mentorId);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        List<MentorDAO> allMentors = mentorService.getAllMentors();
        model.addAttribute("mentors", allMentors);
        return "admin-mentor-page";
    }
}
