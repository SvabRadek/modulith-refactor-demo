package com.cocroachden.modulithrefactordemo.agent;

import com.cocroachden.modulithrefactordemo.account.AccountName;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Price;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Qty;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;

import java.util.List;

public record AgentFill(
        AgentId agentId,
        ExchangeTradeId tradeId,
        ExchangeOrderId orderId,
        List<ContractRepresentation> representations,
        AccountName accountName,
        TradingEnvironment tradingEnvironment,
        Price price,
        Qty qty
) {
}
