package com.example.lambda.school.javaorders.repositories;

import com.example.lambda.school.javaorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepo extends CrudRepository<Payment, Long> {
}
