package com.example.lambda.school.javaorders.controllers;


import com.example.lambda.school.javaorders.models.Customer;
import com.example.lambda.school.javaorders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

//    /all
    @GetMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<?> listAllCustomers(){
        List<Customer> rtnList = customerServices.findAllCustomerOrders();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

//    /id/{id}
    @GetMapping(value = "/id/{custcode}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long custcode){

        Customer rtnCust = customerServices.findCustomerById(custcode);
        return new ResponseEntity<>(rtnCust, HttpStatus.OK);

    }

//    /namelike/{subname}
    @GetMapping(value = "/namelike/{subname}", produces = "application/json")
    public ResponseEntity<?> findCustomerByNameLike(@PathVariable String subname){

        List<Customer> rtnList = customerServices.findByNameLike(subname);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }


}
