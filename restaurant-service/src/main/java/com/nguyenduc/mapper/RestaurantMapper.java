package com.nguyenduc.mapper;

import com.nguyenduc.model.Payment;
import com.nguyenduc.model.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class RestaurantMapper {

    public Restaurant convert(Payment payment) {
        Restaurant restaurant = new Restaurant();
        restaurant.setOrderId(payment.getOrderId());
        restaurant.setSellerId(payment.getSellerId());
        restaurant.setPaymentStatus(payment.getPaymentStatus());
        restaurant.setRestaurantStatus("PREPARE_ORDER");
        return restaurant;
    }
}
