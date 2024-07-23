package uz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.domain.DTO.CodeDTO;
import uz.web.domain.DTO.EmailDTO;
import uz.web.service.UserVerificationService;

@Controller
@RequestMapping("/verification")
public class VerificationController {
    private UserVerificationService verificationService;

    @RequestMapping(value = "/send-code-to-email", method = RequestMethod.POST)
    public String sendCodeToEmail(@ModelAttribute EmailDTO emailDTO, Model model){
        model.addAttribute("email", emailDTO.getEmail());

        try {
            verificationService.sendCodeToEmail(emailDTO.getEmail());
            return "enter-verification-code";
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    @RequestMapping(value = "/check-code", method = RequestMethod.POST)
    public String checkCode(@ModelAttribute CodeDTO codeDTO, Model model){
        model.addAttribute("email", codeDTO.getEmail());

        try {
            verificationService.checkCode(codeDTO.getEmail(), codeDTO.getCode());
            return "user-details";
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "enter-verification-code";
        }
    }
}
