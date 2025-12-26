package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.domain.OrderId;
import com.cocroachden.modulithrefactordemo.account.domain.TradeId;

public class FillAlreadyExistsException extends RuntimeException {
    public FillAlreadyExistsException(TradeId tradeId, OrderId orderId) {
        super("Fill with tradeId [%s] and OrderId [%s] already exists.".formatted(tradeId, orderId));
    }
}
