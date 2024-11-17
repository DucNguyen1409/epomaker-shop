package com.nguyenduc.consumer;

import com.google.gson.Gson;
import com.nguyenduc.model.Payment;
import com.nguyenduc.service.impl.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantConsumer {

    private final RestaurantService restaurantService;
    private final Gson gson;

    @JmsListener(destination = "restaurant-updates", containerFactory = "jmsFactory")
    public void consumer(String restaurantResponse) {
        log.info("{}: fr-Queue [restaurant-updates]: {}", "", restaurantResponse);
        Payment payment = gson.fromJson(restaurantResponse, Payment.class);
        restaurantService.send(payment);
    }
}
