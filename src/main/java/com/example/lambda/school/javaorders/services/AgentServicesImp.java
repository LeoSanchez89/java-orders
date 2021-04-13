package com.example.lambda.school.javaorders.services;


import com.example.lambda.school.javaorders.models.Agent;
import com.example.lambda.school.javaorders.repositories.AgentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "agentServices")
public class AgentServicesImp implements AgentServices{

    @Autowired
    AgentRepo agentrepo;

    @Override
    public Agent findAgentById(long id) {
        return agentrepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Agent " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public Agent save(Agent agent) {
        return agentrepo.save(agent);
    }


}
