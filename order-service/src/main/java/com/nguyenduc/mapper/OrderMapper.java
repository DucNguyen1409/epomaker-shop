package com.nguyenduc.mapper;

import com.nguyenduc.model.Order;
import com.nguyenduc.model.Product;
import com.nguyenduc.shared.CreateOrderRequest;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.data.mapping.KPropertyPathExtensionsKt;
import org.springframework.stereotype.Service;

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
        String time = new TimeStamp(System.currentTimeMillis()).toString();
        order.setCreateTimestamp(time);
        order.setUpdateTimestamp(time);

        return order;
    }
}
