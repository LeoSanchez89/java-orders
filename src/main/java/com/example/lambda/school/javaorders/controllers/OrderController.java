package com.example.lambda.school.javaorders.controllers;


import com.example.lambda.school.javaorders.models.Order;
import com.example.lambda.school.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

//    CRUD ENDPOINTS

//    POST
    @PostMapping(value = "/new", consumes = "application/json")
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody Order newOrder){

        newOrder.setOrdnum(0);
        newOrder = orderServices.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

//    PUT
    @PutMapping(value = "/id/{ordnum}", consumes = "application/json")
    public ResponseEntity<?> updateFullOrder(@Valid @RequestBody Order updateOrder,
                                             @PathVariable long ordnum){
        updateOrder.setOrdnum(ordnum);
        orderServices.save(updateOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    DELETE
    @DeleteMapping(value = "/id/{ordnum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordnum){

        orderServices.delete(ordnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
