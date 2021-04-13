package com.example.lambda.school.javaorders.services;

import com.example.lambda.school.javaorders.models.Agent;

public interface AgentServices {
    Agent save(Agent agent);

    Agent findAgentById(long id);
}
