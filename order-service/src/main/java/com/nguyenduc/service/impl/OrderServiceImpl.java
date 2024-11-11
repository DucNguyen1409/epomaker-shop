package com.nguyenduc.service.impl;

import com.nguyenduc.model.Order;
import com.nguyenduc.repository.OrderRepository;
import com.nguyenduc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
