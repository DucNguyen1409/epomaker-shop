package com.nguyenduc.mapper;

import com.nguyenduc.model.Order;
import com.nguyenduc.model.Product;
import com.nguyenduc.shared.CreateOrderRequest;
import com.nguyenduc.shared.OrderResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrderMapper {

    public Order convert(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderStatus("ORDER_CREATED");
        order.setCustomerId(request.getCustomerId());
        order.setSellerId(request.getSellerId());
        Product product = new Product();
        product.setProductId(request.getProduct().getProductId());
        product.setProductName(request.getProduct().getProductName());
        order.setProduct(product);
        order.setDeliveryLocation(request.getDeliveryLocation());
        order.setPaymentMethod(request.getPaymentMethod().name());
        String time = new Timestamp(System.currentTimeMillis()).toString();
        order.setCreateTimestamp(time);
        order.setUpdateTimestamp(time);

        return order;
    }

    public OrderResponse convert(Order order) {
        com.nguyenduc.shared.Product product = com.nguyenduc.shared.Product.newBuilder()
                .setProductId(order.getProduct().getProductId())
                .setProductName(order.getProduct().getProductName())
                .build();
        return OrderResponse.newBuilder()
                .setCustomerId(order.getCustomerId())
                .setProduct(product)
                .setCustomerId(order.getCustomerId())
                .setDeliveryLocation(order.getDeliveryLocation())
                .setPaymentMethod(order.getPaymentMethod())
                .setOrderStatus(order.getOrderStatus())
                .setSellerId(order.getSellerId())
                .setOrderId(order.getOrderId())
                .setCreatedTime(order.getCreateTimestamp())
                .setUpdateTime(order.getUpdateTimestamp())
                .build();
    }
}
