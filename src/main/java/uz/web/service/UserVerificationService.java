package uz.web.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.web.domain.exceptions.EmailNotFoundException;

@Service
public class UserVerificationService {
    private JavaMailSender javaMailSender;

    public void sendCode(String email){
        String code = getUniqueCode();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setText(code);

        try {
            javaMailSender.send(mailMessage);


        } catch (MailException e){
            throw new EmailNotFoundException();
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