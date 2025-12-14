package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.FillId;
import com.cocroachden.modulithrefactordemo.account.OrderId;
import com.cocroachden.modulithrefactordemo.account.TradeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FillRepository extends JpaRepository<FillEntity, FillId> {
    Boolean existsByTradeIdAndOrderId(TradeId tradeId, OrderId orderId);
}
