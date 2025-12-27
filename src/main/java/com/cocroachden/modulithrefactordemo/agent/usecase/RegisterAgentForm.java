package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.agent.AgentId;

public record RegisterAgentForm(AgentId agentId, TradingEnvironment tradingEnvironment) {
}
