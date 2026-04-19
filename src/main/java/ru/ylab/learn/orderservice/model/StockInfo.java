package ru.ylab.learn.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Внутренняя модель Order-Service.
 * Сюда мы маппим данные и из gRPC (Protobuf), и из REST (Feign).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInfo {
    private int productId;
    private String productName;
    private boolean isAvailable;
    private int quantity;
}