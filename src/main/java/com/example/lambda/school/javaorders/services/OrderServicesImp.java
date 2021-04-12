package com.example.lambda.school.javaorders.services;


import com.example.lambda.school.javaorders.models.Order;
import com.example.lambda.school.javaorders.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value = "orderServices")
public class OrderServicesImp implements OrderServices{

    @Autowired
    OrderRepo orderrepo;

    @Transactional
    @Override
    public Order save(Order order) {
        return orderrepo.save(order);
    }
}
