package com.nguyenduc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "payment")
public class Payment {

    @Id
    private String paymentId;
    private String orderId;
    private Integer customerId;
    private Integer sellerId;
    private String paymentMethod;
    private String paymentStatus;
    private String paymentStatusNotes;

}
