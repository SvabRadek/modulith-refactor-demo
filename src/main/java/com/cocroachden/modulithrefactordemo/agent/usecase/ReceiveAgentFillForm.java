package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.account.domain.AccountName;
import com.cocroachden.modulithrefactordemo.account.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.agent.domain.AgentId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.account.domain.OrderId;
import com.cocroachden.modulithrefactordemo.account.domain.Price;
import com.cocroachden.modulithrefactordemo.account.domain.Qty;
import com.cocroachden.modulithrefactordemo.account.domain.TradeId;

import java.time.Instant;
import java.util.List;

public record ReceiveAgentFillForm(
        AgentId agentId,
        TradeId tradeId,
        OrderId orderId,
        AccountName accountName,
        TradingEnvironment tradingEnvironment,
        List<ContractRepresentation> representations,
        Price price,
        Qty qty,
        Instant recordedAt
) {
}
