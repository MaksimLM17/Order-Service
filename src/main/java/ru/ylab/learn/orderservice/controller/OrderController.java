package ru.ylab.learn.orderservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ylab.learn.orderservice.dto.OrderCreateDto;
import ru.ylab.learn.orderservice.dto.OrderResponseDto;
import ru.ylab.learn.orderservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Valid OrderCreateDto dto) {
        log.info("REST: Запрос на создание заказа (Product: {})", dto.productId());
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(orderService.createOrder(dto.productId(), dto.quantity()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Integer orderId) {
        log.info("REST: Запрос заказа №{}", orderId);
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        log.info("REST: Запрос всех заказов");
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}