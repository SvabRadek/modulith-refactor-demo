package com.cocroachden.modulithrefactordemo.fill.usecase;

import com.cocroachden.modulithrefactordemo.fill.domain.OrderId;
import com.cocroachden.modulithrefactordemo.fill.domain.TradeId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;

import java.util.List;

public class ContractNotFoundException extends RuntimeException {
    public ContractNotFoundException(
            TradeId tradeId,
            OrderId orderId,
            List<ContractRepresentation> representations
    ) {
        super("Contract not found!");
    }
}
