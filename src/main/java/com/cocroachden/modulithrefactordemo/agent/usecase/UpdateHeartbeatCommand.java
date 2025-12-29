package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.agent.AgentId;

import java.time.Instant;

public record UpdateHeartbeatCommand(
        AgentId agentId,
        Instant instant
) {
}
