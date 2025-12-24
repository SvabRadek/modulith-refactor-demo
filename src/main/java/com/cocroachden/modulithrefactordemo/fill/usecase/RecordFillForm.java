package com.cocroachden.modulithrefactordemo.fill.usecase;

import com.cocroachden.modulithrefactordemo.account.domain.*;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.fill.domain.OrderId;
import com.cocroachden.modulithrefactordemo.fill.domain.Price;
import com.cocroachden.modulithrefactordemo.fill.domain.Qty;
import com.cocroachden.modulithrefactordemo.fill.domain.TradeId;

import java.util.List;
import java.util.Objects;

public record RecordFillForm(
        TradeId tradeId,
        OrderId orderId,
        AccountId accountId,
        List<ContractRepresentation> representations,
        Price price,
        Qty qty
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
