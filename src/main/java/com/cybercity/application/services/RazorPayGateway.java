//package com.cybercity.application.services;
//
//import com.cybercity.application.dtos.PaymentDTO;
//import com.cybercity.application.entities.PaymentEntity;
//import com.cybercity.application.entities.enums.PaymentStatus;
//import com.cybercity.application.repositories.PaymentRepository;
//import com.razorpay.Payment;
//import com.razorpay.PaymentLink;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//import org.json.JSONObject;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//
//@Service
//public class RazorPayGateway implements PaymentGateway {
//    private final RazorpayClient razorpayClient;
//    private final PaymentRepository paymentRepository;
//
//    public RazorPayGateway(RazorpayClient razorpayClient, PaymentRepository paymentRepository) {
//        this.razorpayClient = razorpayClient;
//        this.paymentRepository = paymentRepository;
//    }
//
//
//
//    @Override
//    public String createPaymentLink(PaymentDTO paymentDTO,String orderId) {
//         /*
//           There are generally two ways to integrate with a 3rd party
//           1. Make an api call
//           2. Client sdk (Code in a jar)
//        */
//
//        JSONObject paymentLinkRequest = new JSONObject();
//        paymentLinkRequest.put("amount",paymentDTO.getAmount());
//        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("expire_by", LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
//        paymentLinkRequest.put("reference_id",orderId);
//        paymentLinkRequest.put("description","Payment for order no " + orderId);
//
//        PaymentEntity paymentEntity = paymentRepository.findByOrderId(orderId).get();
//
//
//        JSONObject customer = new JSONObject();
//        customer.put("name",paymentEntity.getUserEntity().getUserName());
//        customer.put("contact",paymentEntity.getUserEntity().getPhone());
//        customer.put("email",paymentEntity.getUserEntity().getEmail());
//        paymentLinkRequest.put("customer",customer);
//
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Transaction");
//        paymentLinkRequest.put("notes",notes);
//        paymentLinkRequest.put("callback_url","https://www.youtube.com");
//        paymentLinkRequest.put("callback_method","get");
//
//        try {
//            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
//            return payment.get("short_url");
//        } catch (RazorpayException e) {
//            throw new RuntimeException("Failed to create payment link", e);
//        }
//    }
//
//
//
//
//    @Override
//    public PaymentStatus getStatus(String paymentId, String orderId) {
//
//        try {
//            Payment payment = razorpayClient.payments.fetch(paymentId);
//            String statusType = payment.get("status");
//            return switch (statusType) {
//                case "captured" -> PaymentStatus.SUCCESS;
//                case "failed" -> PaymentStatus.FAILURE;
//                default -> PaymentStatus.INITIATED;
//            };
//        } catch (RazorpayException e) {
//            throw new RuntimeException("Unable to fetch the payment details", e);
//        }
//    }
//}
