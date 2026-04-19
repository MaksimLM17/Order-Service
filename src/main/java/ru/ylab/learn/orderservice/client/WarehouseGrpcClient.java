package ru.ylab.learn.orderservice.client;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ru.ylab.team.lib.ReserveStockRequest;
import ru.ylab.team.lib.StockResponse;
import ru.ylab.team.lib.WarehouseServiceGrpc;
import ru.ylab.team.lib.dto.StockDto;

@Slf4j
@Component
@ConditionalOnProperty(name = "warehouse.client.type", havingValue = "grpc")
public class WarehouseGrpcClient implements WarehouseClient {

    @GrpcClient("warehouse-service")
    private WarehouseServiceGrpc.WarehouseServiceBlockingStub blockingStub;

    @Override
    public StockDto reserveStock(Integer productId, Integer quantity) {
        log.info(">>> [gRPC] Резерв товара: {}", productId);

        ReserveStockRequest request = ReserveStockRequest.newBuilder()
            .setProductId(productId)
            .setQuantity(quantity)
            .build();

        StockResponse response = blockingStub.reserveStock(request);

        // Маппим Proto-ответ в наш DTO
        return new StockDto(
            response.getProductName(),
            response.getIsAvailable(),
            request.getQuantity()
            );
    }
}