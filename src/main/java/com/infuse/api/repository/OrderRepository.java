package com.infuse.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infuse.api.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
