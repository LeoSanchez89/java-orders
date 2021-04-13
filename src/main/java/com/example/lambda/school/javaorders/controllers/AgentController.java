package com.example.lambda.school.javaorders.controllers;


import com.example.lambda.school.javaorders.models.Agent;
import com.example.lambda.school.javaorders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentServices agentServices;

//    /id/{id}
    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<?> findAgentById(@PathVariable long id){

        Agent rtnAgent = agentServices.findAgentById(id);
        return new ResponseEntity<>(rtnAgent, HttpStatus.OK);
    }
}
