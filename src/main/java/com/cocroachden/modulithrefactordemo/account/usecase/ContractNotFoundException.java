package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;

import java.util.List;

public class ContractNotFoundException extends RuntimeException {
    public ContractNotFoundException(
            ExchangeTradeId tradeId,
            ExchangeOrderId orderId,
            List<ContractRepresentation> representations
    ) {
        super("Contract not found!");
    }
}
