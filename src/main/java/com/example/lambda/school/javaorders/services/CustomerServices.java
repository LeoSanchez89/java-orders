package com.example.lambda.school.javaorders.services;

import com.example.lambda.school.javaorders.models.Customer;

import java.util.List;

public interface CustomerServices {
    Customer save(Customer customer);

    List<Customer> findAllCustomerOrders();
    Customer findCustomerById(long id);
    List<Customer> findByNameLike(String subname);
    void delete(long id);
    Customer update(Customer customer, long id);
}
