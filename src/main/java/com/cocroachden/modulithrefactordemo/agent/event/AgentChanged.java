package com.cocroachden.modulithrefactordemo.agent.event;

import com.cocroachden.modulithrefactordemo.agent.domain.Agent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AgentChanged {
    private final Agent agent;
}
