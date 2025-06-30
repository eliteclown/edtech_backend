//package com.cybercity.application.controllers;
//
//import com.cybercity.application.dtos.PaymentDTO;
//import com.cybercity.application.entities.enums.PaymentStatus;
//import com.cybercity.application.services.PaymentService;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//
//public class PaymentController {
//    private final PaymentService paymentService;
//
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    @PostMapping("/payment/createLink")
//    public String createPaymentLink(@RequestBody PaymentDTO inputDTO,@RequestParam String orderId){
//        return paymentService.createLink(inputDTO,orderId);
//    }
//
//    @GetMapping("/payment/getPaymentStatus")
//    public PaymentStatus getPaymentStatus(@RequestParam("paymentId") String paymentId, @RequestParam("orderId") String orderId){
//        return paymentService.getPaymentStatus(paymentId, orderId);
//    }
//}
