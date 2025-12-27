package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.agent.AgentId;

public class AgentNotFoundException extends RuntimeException {
    public AgentNotFoundException(AgentId agentId) {
        super("Agent %s not found!".formatted(agentId.getId()));
    }
}
