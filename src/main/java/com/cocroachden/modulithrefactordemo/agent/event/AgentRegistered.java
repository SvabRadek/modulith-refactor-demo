package com.cocroachden.modulithrefactordemo.agent.event;

import com.cocroachden.modulithrefactordemo.agent.Agent;

public final class AgentRegistered extends AgentChanged {
    public AgentRegistered(Agent agent) {
        super(agent);
    }
}
