package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.fill.domain.FillId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FillRepository extends JpaRepository<FillEntity, FillId> {
    @Transactional(readOnly = true)
    Boolean existsByTradeIdAndOrderId(ExchangeTradeId tradeId, ExchangeOrderId orderId);
}
