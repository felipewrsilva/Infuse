package com.infuse.api.service;

import com.infuse.api.model.Order;
import com.infuse.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        Iterable<Order> orderIterable = orderRepository.findAll();
        return StreamSupport.stream(orderIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Order> getOrderById(Integer orderId) {
        // return orderRepository.findById(orderId);
        Order order = new Order();
        order.setCustomer(orderId);
        return Optional.of(order);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
