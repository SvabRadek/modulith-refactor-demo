package com.cocroachden.modulithrefactordemo.agent.event;

import com.cocroachden.modulithrefactordemo.agent.domain.Agent;

public final class AgentRegistered extends AgentChanged {
    public AgentRegistered(Agent agent) {
        super(agent);
    }
}
