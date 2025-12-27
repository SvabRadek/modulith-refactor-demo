package com.cocroachden.modulithrefactordemo.fill.messaging;

import com.cocroachden.modulithrefactordemo.account.AccountName;
import com.cocroachden.modulithrefactordemo.agent.AgentId;
import com.cocroachden.modulithrefactordemo.agent.query.AgentQuery;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.fill.usecase.RecordFillForm;
import com.cocroachden.modulithrefactordemo.fill.usecase.RecordFillUseCase;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaFillConsumer {

    private final RecordFillUseCase recordFillUseCase;
    private final AgentQuery agentQuery;

    public void onFill() {
        var agentId = AgentId.random();
        var fillTradingEnvironment = TradingEnvironment.LIVE;
        var agent = agentQuery.findById(agentId);
        if (agent.isEmpty()) {
            // warn that agent is missing
        }
        if (agent.get().tradingEnvironment() != fillTradingEnvironment) {
            // warn that trading environment is wrong
        }

        recordFillUseCase.handle(new RecordFillForm(
                new ExchangeTradeId(UUID.randomUUID().toString()),
                new ExchangeOrderId(UUID.randomUUID().toString()),
                new AccountName("Account1"),
                TradingEnvironment.LIVE,
                List.of(
                        new ContractRepresentation("TT", "EEX;TTF;Dec25")
                ),
                new Price(1L),
                new Qty(5L)
        ));
    }

}
