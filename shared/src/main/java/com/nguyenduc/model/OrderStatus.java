package com.nguyenduc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderStatus {
    private String orderId;
    private OrderStates orderStatus;
    private String message;
}
