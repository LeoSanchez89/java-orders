package com.example.lambda.school.javaorders.repositories;

import com.example.lambda.school.javaorders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

    List<Customer> findByCustnameContainingIgnoringCase(String likename);
}
