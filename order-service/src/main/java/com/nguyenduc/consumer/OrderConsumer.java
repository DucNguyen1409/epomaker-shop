package com.nguyenduc.consumer;

import com.google.gson.Gson;
import com.nguyenduc.model.Order;
import com.nguyenduc.model.OrderStatus;
import com.nguyenduc.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderService orderService;
    private final Gson gson;

    @JmsListener(destination = "order-updates", containerFactory = "jmsListenerContainerFactory")
    public void consumer(String message) throws Exception {
        log.info("{} fr-Queue [order-updates]: {}", "", message);
        OrderStatus orderStatus = gson.fromJson(message, OrderStatus.class);
        Order orderById = orderService.updateOrderStatusById(
                orderStatus.getOrderId(),
                orderStatus.getOrderStatus().name()
        );

    }
}
