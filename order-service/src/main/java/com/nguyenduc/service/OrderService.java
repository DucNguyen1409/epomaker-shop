package com.nguyenduc.service;

import com.nguyenduc.model.Order;

public interface OrderService {
    Order save(Order order);
    Order getOrderById(String orderId);
}
