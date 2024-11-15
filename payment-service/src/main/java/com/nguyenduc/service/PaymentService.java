package com.nguyenduc.service;

import com.nguyenduc.mapper.PaymentMapper;
import com.nguyenduc.model.Order;
import com.nguyenduc.model.OrderStates;
import com.nguyenduc.model.OrderStatus;
import com.nguyenduc.model.Payment;
import com.nguyenduc.publisher.PaymentPublisher;
import com.nguyenduc.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final PaymentPublisher paymentPublisher;

    public void send(Order orderResponse) {
        // convert to Payment
        Payment payment = paymentMapper.convert(orderResponse);

        // persist Payment
        paymentRepository.save(payment);

        // publish payment to [restaurant-updates]
        paymentPublisher.publish(payment);

        // publish order PAID to [order-updates]
        paymentPublisher.publish(buildOrderStatus.apply(orderResponse, payment));
    }

    private final BiFunction<Order, Payment, OrderStatus> buildOrderStatus =
            (orderResponse, payment) -> {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderResponse.getOrderId());
        orderStatus.setOrderStatus(OrderStates.ORDER_PAID);
        orderStatus.setMessage("Successfully paid by: " + payment.getPaymentMethod());
        return orderStatus;
    };
}
