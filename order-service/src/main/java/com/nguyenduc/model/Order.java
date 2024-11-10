package com.nguyenduc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Document(collection = "order")
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";
    @Id
    private String orderId;
    private Integer customerId;
    private Integer sellerId;
    private Product product;
    private String paymentMethod;
    private String deliveryLocation;
    private String orderStatus;
    private String createTimestamp;
    private String updateTimestamp;
}
