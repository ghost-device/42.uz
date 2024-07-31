package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.domain.DTO.AcceptPaymentDTO;
import uz.web.domain.DTO.PaymentDTO;
import uz.web.domain.entity.PaymentEntity;
import uz.web.domain.enumerators.PaymentStatus;
import uz.web.service.PaymentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @RequestMapping
    public String paymentPage(Model model) {
        model.addAttribute("pendingPayments", paymentService.allPayments(PaymentStatus.PENDING));
        model.addAttribute("otherPayments", paymentService.allPayments());

        return "admin-payment-control";
    }

    @RequestMapping(value = "/fillbalance", method = RequestMethod.POST)
    public String fillBalance(@ModelAttribute PaymentDTO paymentDTO, Model model){
        try {
            paymentService.fillBalance(paymentDTO);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "main-menu";
    }


    @RequestMapping(value = "/approve/{paymentId}", method = RequestMethod.POST)
    public String acceptPayment(AcceptPaymentDTO acceptPaymentDTO, Model model){
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