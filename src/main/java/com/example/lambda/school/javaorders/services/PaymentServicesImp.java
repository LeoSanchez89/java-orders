package com.example.lambda.school.javaorders.services;


import com.example.lambda.school.javaorders.models.Payment;
import com.example.lambda.school.javaorders.repositories.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value = "paymentServices")
public class PaymentServicesImp implements PaymentServices{

    @Autowired
    PaymentRepo paymentrepo;

    @Transactional
    @Override
    public Payment save(Payment payment) {
        return paymentrepo.save(payment);
    }
}
