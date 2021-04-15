package com.example.lambda.school.javaorders.controllers;


import com.example.lambda.school.javaorders.models.Customer;
import com.example.lambda.school.javaorders.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

/********************************
******** CRUD ENDPOINTS *********
********************************/

//    POST
    @PostMapping(value = "/new", consumes = "application/json")
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCustomer){
        newCustomer.setCustcode(0);
        newCustomer = customerServices.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

//    PUT
    @PutMapping(value = "/id/{custcode}", consumes = "application/json")
    public ResponseEntity<?> updateFullCustomer(@Valid @RequestBody Customer updateCustomer,
                                                @PathVariable long custcode){
        updateCustomer.setCustcode(custcode);
        customerServices.save(updateCustomer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    PATCH
    @PatchMapping(value = "/id/{custcode}", consumes = "application/json")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer updateCustomer,
                                            @PathVariable long custcode){
        customerServices.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    DELETE
    @DeleteMapping(value = "/id/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode){
        customerServices.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
