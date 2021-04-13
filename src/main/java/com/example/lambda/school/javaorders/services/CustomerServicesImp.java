package com.example.lambda.school.javaorders.services;


import com.example.lambda.school.javaorders.models.Customer;
import com.example.lambda.school.javaorders.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerServices")
public class CustomerServicesImp implements CustomerServices{
    @Override
    public Customer findCustomerById(long id) {
        return customerrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));
    }

    @Override
    public List<Customer> findByNameLike(String subname) {

        List<Customer> rtnList = customerrepo.findByCustnameContainingIgnoringCase(subname);
        return rtnList;
    }

    @Override
    public List<Customer> findAllCustomerOrders() {
        List<Customer> list = new ArrayList<>();
        customerrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Autowired
    CustomerRepo customerrepo;



    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerrepo.save(customer);
    }
}
