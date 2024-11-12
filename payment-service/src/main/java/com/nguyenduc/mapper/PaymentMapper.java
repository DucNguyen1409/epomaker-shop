package com.nguyenduc.mapper;

import com.nguyenduc.model.Order;
import com.nguyenduc.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment convert(Order orderResponse) {
        Payment payment = new Payment();
        payment.setOrderId(orderResponse.getOrderId());
        payment.setCustomerId(orderResponse.getCustomerId());
        payment.setSellerId(orderResponse.getSellerId());
        payment.setPaymentMethod(orderResponse.getPaymentMethod());
        payment.setPaymentStatus("SUCCESS");

        return payment;
    }
}
