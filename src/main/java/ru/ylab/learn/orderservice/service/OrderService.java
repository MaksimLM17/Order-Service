package ru.ylab.learn.orderservice.service;

import ru.ylab.learn.orderservice.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {

    OrderResponseDto createOrder(Integer productId, int quantity);

    OrderResponseDto getOrderById(Integer orderId);

    List<OrderResponseDto> getAllOrders();

}
