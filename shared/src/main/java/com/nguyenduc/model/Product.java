package com.nguyenduc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "product")
public class Product {
    @Id
    private Integer productId;
    private String productName;
}
