package com.ks.orderservice.controller;

import com.ks.orderservice.common.TransactionRequest;
import com.ks.orderservice.common.TransactionResponse;
import com.ks.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class orderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){
       return orderService.saveOrder(request);
        }
}
