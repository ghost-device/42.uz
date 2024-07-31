package uz.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.web.domain.DAO.PaymentHistoryDAO;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.DTO.AcceptPaymentDTO;
import uz.web.domain.DTO.PaymentDTO;
import uz.web.domain.enumerators.PaymentStatus;
import uz.web.service.PaymentService;
import uz.web.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;

    @RequestMapping
    public String paymentPage(Model model) {
        model.addAttribute("pendingPayments", paymentService.allPayments(PaymentStatus.PENDING));
        model.addAttribute("otherPayments", paymentService.allPayments());

        return "admin-payment-control";
    }

    @RequestMapping(value = "/fill-balance", method = RequestMethod.POST)
    public String fillBalance(@ModelAttribute PaymentDTO paymentDTO,
                              @RequestParam("checkImg") MultipartFile file,
                              HttpSession session,
                              Model model) {
        try {
            paymentService.fillBalance(paymentDTO, file);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("payments", paymentService.paymentHistoryOfUser(((UserDao) session.getAttribute("user")).getId()));

        return "user-payment";
    }

    @RequestMapping("/user/{userId}")
    public String userPayments(@PathVariable("userId") UUID userId, Model model) {
        List<PaymentHistoryDAO> paymentHistoryDAOS = paymentService.paymentHistoryOfUser(userId);

        model.addAttribute("payments", paymentHistoryDAOS);
        return "user-payment";
    }


    @RequestMapping(value = "/approve/{paymentId}", method = RequestMethod.POST)
    public String acceptPayment(AcceptPaymentDTO acceptPaymentDTO, Model model) {
        try {
            paymentService.acceptPayment(acceptPaymentDTO);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("pendingPayments", paymentService.allPayments(PaymentStatus.PENDING));
        model.addAttribute("otherPayments", paymentService.allPayments());

        return "admin-payment-control";
    }
}