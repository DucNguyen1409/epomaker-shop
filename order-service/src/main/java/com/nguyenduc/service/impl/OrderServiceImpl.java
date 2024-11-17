package com.nguyenduc.service.impl;

import com.nguyenduc.model.Order;
import com.nguyenduc.repository.OrderRepository;
import com.nguyenduc.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(String orderId) {
        Optional<Order> optOrderById = orderRepository.findById(orderId);
        return optOrderById.orElse(new Order());
    }

    @Override
    public Order updateOrderStatusById(String orderId, String status) throws Exception {
        log.info("{}: updateOrderStatusById: {} - {}", "", orderId, status);
        Order orderById = getOrderById(orderId);
        if (Objects.isNull(orderById.getOrderId())) {
            throw new Exception("Not found with orderId: " + orderId);
        }

        // update order status
        orderById.setOrderStatus(status);
        orderById.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        orderRepository.save(orderById);
        return orderById;
    }

}
