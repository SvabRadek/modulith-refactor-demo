package com.cocroachden.modulithrefactordemo.agent.event;

import com.cocroachden.modulithrefactordemo.agent.Agent;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public class AgentHeartbeatUpdated extends AgentChanged{
    public AgentHeartbeatUpdated(Agent agent) {
        super(agent);
    }
}
