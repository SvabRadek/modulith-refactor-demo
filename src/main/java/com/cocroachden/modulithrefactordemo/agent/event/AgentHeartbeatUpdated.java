package com.cocroachden.modulithrefactordemo.agent.event;

import com.cocroachden.modulithrefactordemo.agent.domain.Agent;

public class AgentHeartbeatUpdated extends AgentChanged{
    public AgentHeartbeatUpdated(Agent agent) {
        super(agent);
    }
}
