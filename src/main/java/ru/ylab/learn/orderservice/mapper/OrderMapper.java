package ru.ylab.learn.orderservice.mapper;

import org.springframework.stereotype.Component;
import ru.ylab.learn.orderservice.dto.OrderResponseDto;
import ru.ylab.learn.orderservice.model.OrderInfo;

@Component
public class OrderMapper {

    public OrderResponseDto toDto(OrderInfo info) {
        if (info == null) return null;
        return new OrderResponseDto(
            info.getOrderId(),
            info.getProductId(),
            info.getQuantity(),
            info.getStatus()
        );
    }
}
