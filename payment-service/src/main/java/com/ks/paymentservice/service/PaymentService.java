package com.ks.paymentservice.service;

import com.ks.paymentservice.entity.Payment;
import com.ks.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    public  String paymentProcessing(){
        //api should be 3rd party payment gateway (paypal, paytm...)
        return new Random().nextBoolean()?"success":"false";
    }

    public Optional<Payment> findPaymentHistoryByOrderId(int orderId) {
        return paymentRepository.findById(orderId);
    }
}
