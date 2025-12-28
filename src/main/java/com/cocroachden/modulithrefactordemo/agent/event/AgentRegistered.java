package com.cocroachden.modulithrefactordemo.agent.event;

import com.cocroachden.modulithrefactordemo.agent.Agent;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public final class AgentRegistered extends AgentChanged {
    public AgentRegistered(Agent agent) {
        super(agent);
    }
}
