package com.nguyenduc.publisher;

import com.google.gson.Gson;
import com.nguyenduc.model.OrderStatus;
import com.nguyenduc.model.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantPublisher {

    private final JmsTemplate jmsTemplate;
    private final Gson gson;

    public void publish(Restaurant restaurant) {
        log.info("{}: to-Queue [delivery-updates]: {}", restaurant.getOrderId(), restaurant);
        String jsonString = gson.toJson(restaurant);
        jmsTemplate.convertAndSend("delivery-updates", jsonString);
    }

    public void publish(OrderStatus orderStatus) {
        log.info("{}: to-Queue [order-updates]: {}", orderStatus.getOrderId(), orderStatus);
        String jsonString = gson.toJson(orderStatus);
        jmsTemplate.convertAndSend("order-updates", jsonString);
    }
}
