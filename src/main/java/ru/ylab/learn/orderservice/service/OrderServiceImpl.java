package ru.ylab.learn.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ylab.learn.orderservice.client.WarehouseClient;
import ru.ylab.learn.orderservice.dto.OrderResponseDto;
import ru.ylab.learn.orderservice.exception.InsufficientStockException;
import ru.ylab.learn.orderservice.exception.NotFoundException;
import ru.ylab.learn.orderservice.mapper.OrderMapper;
import ru.ylab.learn.orderservice.model.OrderInfo;
import ru.ylab.learn.orderservice.model.Status;
import ru.ylab.learn.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WarehouseClient warehouseClient;
    private final OrderMapper orderMapper;

    private final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public OrderResponseDto createOrder(Integer productId, int quantity) {
        log.info("Сервис: Оформление заказа на товар ID: {}, количество: {}", productId, quantity);

        OrderInfo order = new OrderInfo();
        order.setOrderId(counter.getAndIncrement());
        order.setProductId(productId);
        order.setQuantity(quantity);

        try {
            warehouseClient.reserveStock(productId, quantity);
            order.setStatus(Status.CONFIRMED);
            log.info("Сервис: Резерв подтвержден для заказа №{}", order.getOrderId());
        } catch (Exception e) {
            log.warn("Сервис: Резерв отклонен или склад недоступен: {}", e.getMessage());
            throw new InsufficientStockException("Данного товара нет в нужном количестве, попробуйте уменьшить количество и повторить заказ!");
        }

        OrderInfo savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderResponseDto getOrderById(Integer orderId) {
        log.info("Сервис: Поиск заказа №{}", orderId);
        return orderRepository.findById(orderId)
            .map(orderMapper::toDto)
            .orElseThrow(() -> new NotFoundException("Заказ не найден: " + orderId));
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        log.info("Сервис: Получение списка всех заказов");
        return orderRepository.findAll().stream()
            .map(orderMapper::toDto)
            .toList();
    }
}