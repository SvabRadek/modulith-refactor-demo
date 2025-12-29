package com.cocroachden.modulithrefactordemo.fill.usecase;

import com.cocroachden.modulithrefactordemo.account.AccountName;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Price;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Qty;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;

import java.util.List;
import java.util.Objects;

public record RecordFillCommand(
        ExchangeTradeId tradeId,
        ExchangeOrderId orderId,
        AccountName accountName,
        TradingEnvironment tradingEnvironment,
        List<ContractRepresentation> representations,
        Price price,
        Qty qty
) {
    public RecordFillCommand {
        Objects.requireNonNull(tradeId, "TradeId cannot be null!");
        Objects.requireNonNull(orderId, "OrderId cannot be null!");
        Objects.requireNonNull(accountName, "AccountName cannot be null!");
        Objects.requireNonNull(tradingEnvironment, "TradingEnvironment cannot be null!");
        Objects.requireNonNull(representations, "Representations cannot be null!");
        Objects.requireNonNull(price, "Price cannot be null!");
        Objects.requireNonNull(qty, "Qty cannot be null!");
    }
}
