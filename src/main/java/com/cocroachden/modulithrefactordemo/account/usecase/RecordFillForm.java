package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.AccountId;
import com.cocroachden.modulithrefactordemo.account.OrderId;
import com.cocroachden.modulithrefactordemo.account.TradeId;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;

import java.util.List;
import java.util.Objects;

public record RecordFillForm(
        TradeId tradeId,
        OrderId orderId,
        AccountId accountId,
        List<ContractRepresentation> representations,
        Long price,
        Long qty
) {
    public RecordFillForm {
        Objects.requireNonNull(tradeId, "TradeId cannot be null!");
        Objects.requireNonNull(orderId, "OrderId cannot be null!");
        Objects.requireNonNull(accountId, "AccountId cannot be null!");
        Objects.requireNonNull(representations, "Representations cannot be null!");
        Objects.requireNonNull(price, "Price cannot be null!");
        Objects.requireNonNull(qty, "Qty cannot be null!");
    }
}
