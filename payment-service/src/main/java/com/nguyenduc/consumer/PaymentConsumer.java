package com.nguyenduc.consumer;

import com.google.gson.Gson;
import com.nguyenduc.model.Order;
import com.nguyenduc.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentService paymentService;
    private final Gson gson;

    @JmsListener(destination = "payment-updates", containerFactory = "jmsFactory")
    public void consumer(String orderResponse) {
        log.info("{} fr-Queue [payment-updates]: {}", "", orderResponse);
        Order order = gson.fromJson(orderResponse, Order.class);
        paymentService.send(order);
    }
}
