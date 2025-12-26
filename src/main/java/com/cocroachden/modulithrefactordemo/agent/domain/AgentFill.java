package com.cocroachden.modulithrefactordemo.agent.domain;

import com.cocroachden.modulithrefactordemo.account.domain.AccountName;
import com.cocroachden.modulithrefactordemo.account.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.account.domain.OrderId;
import com.cocroachden.modulithrefactordemo.account.domain.Price;
import com.cocroachden.modulithrefactordemo.account.domain.Qty;
import com.cocroachden.modulithrefactordemo.account.domain.TradeId;

import java.util.List;

public record AgentFill(
        AgentId agentId,
        TradeId tradeId,
        OrderId orderId,
        List<ContractRepresentation> representations,
        AccountName accountName,
        TradingEnvironment tradingEnvironment,
        Price price,
        Qty qty
) {
}
