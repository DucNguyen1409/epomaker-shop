package com.nguyenduc.service.impl;

import com.nguyenduc.mapper.RestaurantMapper;
import com.nguyenduc.model.*;
import com.nguyenduc.publisher.RestaurantPublisher;
import com.nguyenduc.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantMapper restaurantMapper;
    private final RestaurantPublisher restaurantPublisher;
    private final RestaurantRepository restaurantRepository;

    public void send(Payment paymentResponse) {
        // convert to Restaurant
        Restaurant restaurant = restaurantMapper.convert(paymentResponse);

        // persist Restaurant
        restaurantRepository.save(restaurant);

        // publish Restaurant to [delivery-updates]
        restaurantPublisher.publish(restaurant);

        // publish order PREPARED to [order-updates]
        restaurantPublisher.publish(buildOrderStatus.apply(paymentResponse, restaurant));
    }

    //TODO: restaurant delivery order update status

    private final BiFunction<Payment, Restaurant, OrderStatus> buildOrderStatus =
            (paymentResponse, restaurant) -> {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setOrderId(paymentResponse.getOrderId());
                orderStatus.setOrderStatus(OrderStates.ORDER_PREPARED);
                return orderStatus;
            };
}
