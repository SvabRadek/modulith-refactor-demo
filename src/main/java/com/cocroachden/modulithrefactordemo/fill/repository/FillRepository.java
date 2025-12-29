package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.fill.FillId;
import com.cocroachden.modulithrefactordemo.fill.RecordedFill;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FillRepository extends JpaRepository<FillEntity, FillId> {
    @Transactional(readOnly = true)
    Boolean existsByTradeIdAndOrderId(ExchangeTradeId tradeId, ExchangeOrderId orderId);

    @Transactional(readOnly = true)
    Optional<FillEntity> findByTradeIdAndOrderId(ExchangeTradeId tradeId, ExchangeOrderId orderId);
}
