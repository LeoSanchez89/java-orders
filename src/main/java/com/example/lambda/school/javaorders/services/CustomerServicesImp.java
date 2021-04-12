package com.example.lambda.school.javaorders.services;


import com.example.lambda.school.javaorders.models.Customer;
import com.example.lambda.school.javaorders.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImp implements CustomerServices{

    @Autowired
    CustomerRepo customerrepo;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerrepo.save(customer);
    }
}
