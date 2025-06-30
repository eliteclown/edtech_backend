//package com.cybercity.application.services;
//
//import com.cybercity.application.dtos.PaymentDTO;
//import com.cybercity.application.entities.PaymentEntity;
//import com.cybercity.application.entities.enums.PaymentStatus;
//import com.cybercity.application.repositories.PaymentRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//public class PaymentService {
//    private final PaymentGateway paymentGateway;
//    private final PaymentRepository paymentRepository;
//    private final ModelMapper modelMapper;
//
//    public PaymentService(PaymentGateway paymentGateway, PaymentRepository paymentRepository, ModelMapper modelMapper) {
//        this.paymentGateway = paymentGateway;
//        this.paymentRepository = paymentRepository;
//        this.modelMapper = modelMapper;
//    }
//
//
//    public String createLink(PaymentDTO paymentDTO,String orderId){
//        /*
//        Make a call to order service and get the order details.
//        OrderDetail order = restTemplate.getMapping(orderId)
//        name = order.getCustomerName()
//        amount = order.getAmount()
//        phone = order.getCustomerPhone()
//       */
////        PaymentDTO paymentLinkRequestDto = new PaymentDTO()
////        paymentLinkRequestDto.setCustomerName("Sanjay");
////        paymentLinkRequestDto.setOrderId(orderId);
////        paymentLinkRequestDto.setPhone("8310206130");
////        paymentLinkRequestDto.setAmount(100);
//
//        // Generate payment link using the payment gateway
//        String paymentLink = paymentGateway.createPaymentLink(paymentDTO,orderId);
//
//        // Save payment details in the repository
////        PaymentDetails paymentResponse = new PaymentDetails();
////        paymentResponse.setPaymentLink(paymentLink);
//        PaymentEntity paymentEntity = modelMapper.map(paymentDTO,PaymentEntity.class);
//        paymentEntity.setOrderId(orderId);
////        paymentResponse.setOrderId(orderId);
//        paymentRepository.save(paymentEntity);
//
//        return paymentLink;
//    }
//
//    public PaymentStatus getPaymentStatus(String paymentId, String orderId) {
//        // Retrieve payment details by order ID
////        Optional<PaymentDetails> paymentDetails = paymentRepository.findByOrderId(orderId);
//        Optional<PaymentEntity> paymentEntity = paymentRepository.findByOrderId(orderId);
//
//        if(paymentEntity.isEmpty()){
//            throw new RuntimeException("Payment not found");
//        }
//
//        // Get payment status from the payment gateway
//        PaymentStatus status = paymentGateway.getStatus(paymentId, orderId);
//
//        // Update and save payment details with the new status
////        PaymentDetails paymentResponse = paymentDetails.get();
////        paymentResponse.setStatus(status);
////        paymentResponse.setPaymentId(paymentId);
//
//        PaymentEntity paymentResponse = paymentEntity.get();
//        Set<PaymentStatus> result=new HashSet<>();
//        result=Set.of(status);
//        paymentResponse.setStatus(result);
//        paymentResponse.setPaymentId(Long.valueOf(paymentId));
//        paymentRepository.save(paymentResponse);
//
//        return status;
//    }
//}
