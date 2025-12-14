package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.OrderId;
import com.cocroachden.modulithrefactordemo.account.TradeId;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;

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
