package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;

public class FillAlreadyExistsException extends RuntimeException {
    public FillAlreadyExistsException(ExchangeTradeId tradeId, ExchangeOrderId orderId) {
        super("Fill with tradeId [%s] and OrderId [%s] already exists.".formatted(tradeId, orderId));
    }
}
