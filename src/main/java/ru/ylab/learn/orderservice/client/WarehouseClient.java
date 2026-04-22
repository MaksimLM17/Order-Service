package ru.ylab.learn.orderservice.client;

import ru.ylab.team.lib.dto.StockDto;

public interface WarehouseClient {

    StockDto reserveStock(Integer productId, Integer quantity);
}