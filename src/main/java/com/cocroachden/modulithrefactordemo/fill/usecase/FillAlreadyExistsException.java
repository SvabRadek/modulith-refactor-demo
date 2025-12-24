package com.cocroachden.modulithrefactordemo.fill.usecase;

import com.cocroachden.modulithrefactordemo.fill.domain.OrderId;
import com.cocroachden.modulithrefactordemo.fill.domain.TradeId;

public class FillAlreadyExistsException extends RuntimeException {
    public FillAlreadyExistsException(TradeId tradeId, OrderId orderId) {
        super("Fill with tradeId [%s] and OrderId [%s] already exists.".formatted(tradeId, orderId));
    }
}
