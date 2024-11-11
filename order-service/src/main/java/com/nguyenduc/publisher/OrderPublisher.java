package com.nguyenduc.publisher;

import com.google.gson.Gson;
import com.nguyenduc.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPublisher {

    private final JmsTemplate jmsTemplate;
    private final Gson gson;

    public void publish(Order order) {
        log.info("{}: to Queue payment-updates: {}", order.getOrderId(), order);
        String jsonString = gson.toJson(order);
        jmsTemplate.convertAndSend("payment-updates", jsonString);
    }
}
