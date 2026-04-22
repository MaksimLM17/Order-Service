package ru.ylab.learn.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {
    private int productId;
    private String productName;
    private boolean isAvailable;
    private int quantity;
}