package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.domain.DTO.CodeDTO;
import uz.web.domain.DTO.EmailDTO;
import uz.web.service.UserVerificationService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/verification")
public class VerificationController {
    private final UserVerificationService userVerificationService;

    @RequestMapping("/send-code-to-email")
    public String throwToSendCodePage(){
        return "send-code-to-email";
    }

    @RequestMapping(value = "/send-code-to-email", method = RequestMethod.POST)
    public String sendCodeToEmail(@ModelAttribute EmailDTO emailDTO, Model model){
        model.addAttribute("email", emailDTO.getEmail());

        userVerificationService.sendCodeToEmail(emailDTO.getEmail());
        return "enter-verification-code";
    }

    @RequestMapping(value = "/check-code", method = RequestMethod.POST)
    public String checkCode(@ModelAttribute CodeDTO codeDTO, Model model){
        model.addAttribute("email", codeDTO.getEmail());

        try {
            userVerificationService.checkCode(codeDTO.getEmail(), codeDTO.getCode());
            return "user-details";
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "enter-verification-code";
        }
    }
}