package com.nguyenduc.publisher;

import com.google.gson.Gson;
import com.nguyenduc.model.OrderStatus;
import com.nguyenduc.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentPublisher {

    private final JmsTemplate jmsTemplate;
    private final Gson gson;

    public void publish(Payment payment) {
        log.info("{}: to-Queue [restaurant-updates]: {}", payment.getPaymentId(), payment);
        String jsonString = gson.toJson(payment);
        jmsTemplate.convertAndSend("restaurant-updates", jsonString);
    }

    public void publish(OrderStatus orderStatus) {
        log.info("{}: to-Queue [order-updates]: {}", orderStatus.getOrderId(), orderStatus);
        String jsonString = gson.toJson(orderStatus);
        jmsTemplate.convertAndSend("order-updates", jsonString);
    }
}
