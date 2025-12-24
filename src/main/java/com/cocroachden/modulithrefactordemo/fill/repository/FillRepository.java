package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.fill.domain.FillId;
import com.cocroachden.modulithrefactordemo.fill.domain.OrderId;
import com.cocroachden.modulithrefactordemo.fill.domain.TradeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FillRepository extends JpaRepository<FillEntity, FillId> {
    Boolean existsByTradeIdAndOrderId(TradeId tradeId, OrderId orderId);
}
