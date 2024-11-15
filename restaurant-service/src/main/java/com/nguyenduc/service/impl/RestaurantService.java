package com.nguyenduc.service.impl;

import com.nguyenduc.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    public void send(Payment paymentResponse) {
        // convert to Restaurant

        // persist Restaurant
        // publish Restaurant to [delivery-updates]

        // publish order PREPARED to [order-updates]

    }
}
