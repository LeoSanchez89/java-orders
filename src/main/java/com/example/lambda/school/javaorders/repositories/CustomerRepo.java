package com.example.lambda.school.javaorders.repositories;

import com.example.lambda.school.javaorders.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
}
