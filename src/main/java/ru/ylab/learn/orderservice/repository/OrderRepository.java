package ru.ylab.learn.orderservice.repository;

import ru.ylab.learn.orderservice.model.OrderInfo;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    OrderInfo save(OrderInfo order);

    Optional<OrderInfo> findById(Integer id);

    List<OrderInfo> findAll();
}

