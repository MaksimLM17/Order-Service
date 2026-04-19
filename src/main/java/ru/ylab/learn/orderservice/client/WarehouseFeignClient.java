package ru.ylab.learn.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ylab.team.lib.dto.StockDto;

@FeignClient(name = "warehouse-service", url = "http://localhost:8081")
public interface WarehouseFeignClient {

    /**
     * Метод для списания/резерва товара через REST.
     * Передаем количество через RequestParam для простоты.
     */
    @PostMapping("/api/v1/stock/{productId}/reserve")
    StockDto reserveStock(
        @PathVariable("productId") Integer productId,
        @RequestParam("quantity") Integer quantity
    );
}