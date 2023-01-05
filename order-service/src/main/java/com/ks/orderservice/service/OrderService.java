package com.ks.orderservice.service;

import com.ks.orderservice.common.Payment;
import com.ks.orderservice.common.TransactionRequest;
import com.ks.orderservice.common.TransactionResponse;
import com.ks.orderservice.entity.Order;
import com.ks.orderservice.reepository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request){

        String response="";
        Order order= request.getOrder();
        Payment payment= request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call
         Payment paymentResponse =template.postForObject( "http://PAYMENT-SERVICE/payment/doPayment",payment, Payment.class);
      response= paymentResponse.getPaymentStatus().equals("success")?"payment processing succesfulk and order placed":"there is a failure in payment ap, order added to cart";

         orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}
