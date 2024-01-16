package com.infuse.api.service;

import com.infuse.api.model.Order;
import com.infuse.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final int DEFAULT_QUANTITY = 1;
    private static final int DISCOUNT_THRESHOLD_LOW = 5;
    private static final int DISCOUNT_THRESHOLD_HIGH = 10;
    private static final BigDecimal DISCOUNT_RATE_LOW = BigDecimal.valueOf(0.05); // 5%
    private static final BigDecimal DISCOUNT_RATE_HIGH = BigDecimal.valueOf(0.10); // 10%
    private static final BigDecimal NO_DISCOUNT = BigDecimal.valueOf(0.0);
    private static final int MAX_ORDERS = 10;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> createOrders(List<Order> orders) {
        if (orders.size() > MAX_ORDERS) {
            throw new IllegalArgumentException("Cannot process more than " + MAX_ORDERS + " orders at a time");
        }
        List<Order> createdOrders = new ArrayList<>();
        for (Order order : orders) {
            try {
                createdOrders.add(createOrder(order));
            } catch (IllegalArgumentException e) {
                // log
            }
        }
        return createdOrders;
    }

    private Order createOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        validateOrder(order);
        setDefaultValues(order);
        applyDiscounts(order);
        calculateTotalValue(order);
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        validateOrder(order);
        applyDiscounts(order);
        calculateTotalValue(order);
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Boolean deleteOrder(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validateOrder(Order order) {
        if (order.getControlNumber() == null || order.getControlNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Control number is required");
        }
        if (order.getProductName() == null || order.getProductName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (order.getUnitPrice() == null || order.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Unit price must be greater than 0");
        }
        if (order.getQuantity() == null || order.getQuantity() < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        if (orderRepository.existsByControlNumber(order.getControlNumber())) {
            throw new IllegalArgumentException("Control number already exists");
        }
    }

    private void setDefaultValues(Order order) {

        if (order.getRegistrationDate() == null) {
            Date currentDate = new Date();
            order.setRegistrationDate(currentDate);
        }
        if (order.getQuantity() == null) {
            order.setQuantity(DEFAULT_QUANTITY);
        }
    }

    private void applyDiscounts(Order order) {
        if (order.getQuantity() > DISCOUNT_THRESHOLD_LOW) {
            order.setDiscount(order.getQuantity() >= DISCOUNT_THRESHOLD_HIGH ? DISCOUNT_RATE_HIGH : DISCOUNT_RATE_LOW);
        } else {
            order.setDiscount(NO_DISCOUNT);
        }
    }

    private void calculateTotalValue(Order order) {
        BigDecimal total = order.getUnitPrice().multiply(new BigDecimal(order.getQuantity()));
        total = total.subtract(total.multiply(order.getDiscount()));
        order.setTotalValue(total);
    }

}
