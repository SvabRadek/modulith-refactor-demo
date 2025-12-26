package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.domain.FillId;
import com.cocroachden.modulithrefactordemo.account.domain.OrderId;
import com.cocroachden.modulithrefactordemo.account.domain.TradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FillRepository extends JpaRepository<FillEntity, FillId> {
    @Transactional(readOnly = true)
    Boolean existsByTradeIdAndOrderId(TradeId tradeId, OrderId orderId);
}
