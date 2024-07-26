package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.web.domain.entity.PaymentEntity;
import uz.web.service.PaymentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentController {

    private final PaymentService paymentService;

    @RequestMapping
    public String paymentPage() {
        List<PaymentEntity> payments = paymentService.allPayments();
        return "admin-payments";
    }
}
