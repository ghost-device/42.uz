package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.domain.DTO.AuthDTO;
import uz.web.domain.DTO.UpdatePasswordDTO;
import uz.web.service.CourseService;
import uz.web.service.MentorService;
import uz.web.service.UserService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final MentorService mentorService;
    private final CourseService courseService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute AuthDTO authDTO, Model model) {
        try {
            userService.register(authDTO);

            return "login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String throwToLoginMenu() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute AuthDTO authDTO, Model model, HttpSession session){
        try {
            session.setAttribute("user", userService.login(authDTO));
            return "redirect:/user/main-menu";
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
    }

    @RequestMapping(value = "/admin-login", method = RequestMethod.POST)
    public String adminLogin(@ModelAttribute AuthDTO authDTO, Model model) {
        if (authDTO.getEmail().equals("admin") && authDTO.getPassword().equals("1")) {
            model.addAttribute("mentors", mentorService.getAllMentors());
            model.addAttribute("courses", courseService.getAllCourse());
            return "admin-dashboard";
        }

        model.addAttribute("errorMessage", "Username yoki parol xato!");
        return "admin-login";
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute UpdatePasswordDTO updatePasswordDTO, Model model) {
        try {
            userService.changePassword(updatePasswordDTO);
            model.addAttribute("successMessage", "Password updated successfully!");
            return "change-password";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "change-password";
        }

    }

}
