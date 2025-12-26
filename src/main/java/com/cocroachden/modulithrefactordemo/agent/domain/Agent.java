package com.cocroachden.modulithrefactordemo.agent.domain;

import com.cocroachden.modulithrefactordemo.account.domain.TradingEnvironment;

import java.time.Instant;

public record Agent(
        AgentId id,
        TradingEnvironment tradingEnvironment,
        Instant lastHeartBeat
) {
}
