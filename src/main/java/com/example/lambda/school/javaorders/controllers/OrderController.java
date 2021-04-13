package com.example.lambda.school.javaorders.controllers;


import com.example.lambda.school.javaorders.models.Order;
import com.example.lambda.school.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

//    id/{ordnum}
    @GetMapping(value = "/id/{ordnum}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long ordnum){

        Order rtnOrd = orderServices.findOrderById(ordnum);
        return new ResponseEntity<>(rtnOrd, HttpStatus.OK);
    }


}
