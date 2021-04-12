package com.example.lambda.school.javaorders.repositories;

import com.example.lambda.school.javaorders.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Long> {
}
