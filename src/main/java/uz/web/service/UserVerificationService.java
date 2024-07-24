package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.web.domain.entity.CodeEntity;
import uz.web.domain.exceptions.EmailNotFoundException;
import uz.web.domain.exceptions.InvalidCodeException;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserVerificationService {
    private final UserService userService;
    private final JavaMailSender javaMailSender;
    private final CodeService codeService;

    public void checkCode(String email, String code){
        CodeEntity codeByEmail = codeService.getCodeByEmail(email);

        if (!codeByEmail.getCode().equals(code)){
            throw new InvalidCodeException("This code is invalid!");
        }

        if (codeByEmail.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new InvalidCodeException("This code is expired!");
        }
    }

    public void sendCodeToEmail(String email){
        String code = getUniqueCode();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Your verification code");
        mailMessage.setText(code);

        try {
            userService.checkEmail(email);

            javaMailSender.send(mailMessage);

            codeService.save(new CodeEntity(email, code, LocalDateTime.now().plusMinutes(1)));
        } catch (Exception e){
            throw new EmailNotFoundException(e.getMessage());
        }
    }

    public static String getUniqueCode() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            sb.append((char) (65 + (int) (Math.random() * ((90 - 65) + 1))));
            sb.append((int) (Math.random() * 10));
        }

        return sb.toString();
    }
}