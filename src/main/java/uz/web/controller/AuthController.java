package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.domain.DTO.AuthDTO;
import uz.web.service.UserService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute AuthDTO authDTO, Model model){
        try {
            userService.register(authDTO);

            return "login";
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String throwToLoginMenu(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute AuthDTO authDTO, Model model){
        try {
            model.addAttribute("user", userService.login(authDTO));

            return "user-main-menu";
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
    }
}
