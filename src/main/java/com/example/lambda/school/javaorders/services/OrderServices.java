package com.example.lambda.school.javaorders.services;

import com.example.lambda.school.javaorders.models.Order;

public interface OrderServices {
    Order save(Order order);

    Order findOrderById(long id);
}
