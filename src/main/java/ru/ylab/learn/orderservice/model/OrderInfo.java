package ru.ylab.learn.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {

    private Integer orderId;

    private Integer productId;

    private int quantity;

    private Status status;
}
