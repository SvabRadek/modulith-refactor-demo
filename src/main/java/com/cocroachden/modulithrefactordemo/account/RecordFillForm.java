package com.cocroachden.modulithrefactordemo.account;

import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;

import java.util.List;

public record RecordFillForm(
        TradeId tradeId,
        OrderId orderId,
        AccountId accountId,
        List<ContractRepresentation> representations,
        Long price,
        Long qty
) {
}
