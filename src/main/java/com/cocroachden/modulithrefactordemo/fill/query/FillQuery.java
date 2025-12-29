package com.cocroachden.modulithrefactordemo.fill.query;

import com.cocroachden.modulithrefactordemo.fill.RecordedFill;
import com.cocroachden.modulithrefactordemo.fill.repository.FillRepository;
import com.cocroachden.modulithrefactordemo.fill.utils.FillUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;
import lombok.RequiredArgsConstructor;
import org.jmolecules.architecture.layered.ApplicationLayer;
import org.springframework.modulith.NamedInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@NamedInterface
@ApplicationLayer
public class FillQuery {

    private final FillRepository fillRepository;

    public Optional<RecordedFill> findFill(ExchangeTradeId tradeId, ExchangeOrderId orderId) {
        return fillRepository.findByTradeIdAndOrderId(tradeId, orderId)
                .map(FillUtils::map);
    }

}
