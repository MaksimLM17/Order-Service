package ru.ylab.learn.orderservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.ylab.team.lib.dto.StockDto;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "warehouse.client.type", havingValue = "rest")
public class WarehouseRestClient implements WarehouseClient {

    private final WarehouseFeignClient feignClient;

    @Override
    public StockDto reserveStock(Integer productId, Integer quantity) {
        log.info(">>> [REST] Резерв товара: {}", productId);
        // Feign уже отдал нам StockDto, просто возвращаем его
        return feignClient.reserveStock(productId, quantity);
    }
}