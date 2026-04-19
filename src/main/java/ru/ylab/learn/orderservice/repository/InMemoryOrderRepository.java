package ru.ylab.learn.orderservice.repository;

import org.springframework.stereotype.Repository;
import ru.ylab.learn.orderservice.model.OrderInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<Integer, OrderInfo> storage = new ConcurrentHashMap<>();

    @Override
    public OrderInfo save(OrderInfo order) {
        storage.put(order.getOrderId(), order);
        return order;
    }

    @Override
    public Optional<OrderInfo> findById(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<OrderInfo> findAll() {
        return new ArrayList<>(storage.values());
    }
}
