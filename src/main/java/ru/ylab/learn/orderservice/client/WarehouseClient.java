package ru.ylab.learn.orderservice.client;

import ru.ylab.team.lib.dto.StockDto;

public interface WarehouseClient {
    // Работаем только с DTO
    StockDto reserveStock(Integer productId, Integer quantity);
}