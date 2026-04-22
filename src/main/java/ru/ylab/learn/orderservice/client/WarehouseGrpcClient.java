package ru.ylab.learn.orderservice.client;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import ru.ylab.team.lib.ReserveStockRequest;
import ru.ylab.team.lib.StockResponse;
import ru.ylab.team.lib.WarehouseServiceGrpc;
import ru.ylab.team.lib.dto.StockDto;

/**
 * Клиентский компонент для взаимодействия с микросервисом склада через gRPC.
 * <p>
 * Данный класс инкапсулирует логику формирования Protobuf-запросов и
 * преобразования бинарных ответов во внутренние DTO приложения.
 */
@Slf4j
@Component
public class WarehouseGrpcClient implements WarehouseClient {

    /**
     * Блокирующая заглушка (Stub) для синхронного вызова методов WarehouseService.
     * Настроена на работу через HTTP/2 соединение.
     */
    @GrpcClient("warehouse-service")
    private WarehouseServiceGrpc.WarehouseServiceBlockingStub blockingStub;

    /**
     * Выполняет операцию резервирования товара на удаленном складе.
     * <p>
     * Метод использует паттерн Builder для создания неизменяемого Protobuf-запроса.
     * Вызов является блокирующим: выполнение текущего потока приостанавливается до получения ответа.
     *
     * @param productId уникальный идентификатор товара.
     * @param quantity  количество единиц товара для резерва.
     * @return {@link StockDto} объект с актуальными данными о товаре и статусе резерва.
     * @throws io.grpc.StatusRuntimeException если сервер вернул ошибку (например, NOT_FOUND или FAILED_PRECONDITION).
     */
    @Override
    public StockDto reserveStock(Integer productId, Integer quantity) {
        log.info(">>> [gRPC] Резерв товара: {}", productId);

        ReserveStockRequest request = ReserveStockRequest.newBuilder()
            .setProductId(productId)
            .setQuantity(quantity)
            .build();

        StockResponse response = blockingStub.reserveStock(request);

        return new StockDto(
            response.getProductName(),
            response.getIsAvailable(),
            request.getQuantity()
            );
    }
}