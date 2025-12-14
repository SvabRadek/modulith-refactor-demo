package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.domain.OrderId;
import com.cocroachden.modulithrefactordemo.account.domain.TradeId;
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
