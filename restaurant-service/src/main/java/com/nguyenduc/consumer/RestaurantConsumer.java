package com.nguyenduc.consumer;

import com.google.gson.Gson;
import com.nguyenduc.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantConsumer {
    private final Gson gson;

    @JmsListener(destination = "restaurent-updates", containerFactory = "jmsFactory")
    public void consumer(String restaurantResponse) {
        log.info("{}: fr-Queue [restaurent-updates]: {}", "", restaurantResponse);
        Payment payment = gson.fromJson(restaurantResponse, Payment.class);


    }
}
