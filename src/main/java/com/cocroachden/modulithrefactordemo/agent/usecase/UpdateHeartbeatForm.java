package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.agent.domain.AgentId;

import java.time.Instant;

public record UpdateHeartbeatForm(
        AgentId agentId,
        Instant instant
) {
}
