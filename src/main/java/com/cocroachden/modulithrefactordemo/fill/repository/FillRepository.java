package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.fill.domain.FillId;
import com.cocroachden.modulithrefactordemo.fill.domain.OrderId;
import com.cocroachden.modulithrefactordemo.fill.domain.TradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FillRepository extends JpaRepository<FillEntity, FillId> {
    @Transactional(readOnly = true)
    Boolean existsByTradeIdAndOrderId(TradeId tradeId, OrderId orderId);
}
