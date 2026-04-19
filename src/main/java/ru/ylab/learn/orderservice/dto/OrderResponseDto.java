package ru.ylab.learn.orderservice.dto;

import ru.ylab.learn.orderservice.model.Status;

public record OrderResponseDto(
    Integer orderId,
    Integer productId,
    int quantity,
    Status status
) {}
