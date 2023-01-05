package com.ks.paymentservice.controller;

import com.ks.paymentservice.entity.Payment;
import com.ks.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment){
        return paymentService.doPayment(payment);
    }
@GetMapping("/{orderId}")
    public Optional<Payment> findPaymentHistoryByOrderId(@PathVariable int orderId){
        return paymentService.findPaymentHistoryByOrderId(orderId);
    }

}
