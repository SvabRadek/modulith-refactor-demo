package com.cocroachden.modulithrefactordemo.agent.messaging;

import com.cocroachden.modulithrefactordemo.account.domain.AccountName;
import com.cocroachden.modulithrefactordemo.account.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.agent.domain.AgentId;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentRepository;
import com.cocroachden.modulithrefactordemo.agent.usecase.*;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.fill.domain.OrderId;
import com.cocroachden.modulithrefactordemo.fill.domain.Price;
import com.cocroachden.modulithrefactordemo.fill.domain.Qty;
import com.cocroachden.modulithrefactordemo.fill.domain.TradeId;
import com.cocroachden.modulithrefactordemo.fill.usecase.RecordFillForm;
import com.cocroachden.modulithrefactordemo.fill.usecase.RecordFillUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private final RegisterAgentUseCase registerAgentUseCase;
    private final UpdateAgentHeartbeatUseCase updateAgentHeartbeatUseCase;
    private final ProcessAgentFillUseCase processAgentFillUseCase;
    private final RecordFillUseCase recordFillUseCase;
    private final AgentRepository agentRepository;

    void onMessage(String message) {
        AgentId random = AgentId.random();
        if (message.contains("heartbeat")) {
            if (agentRepository.existsById(random)) {
                updateAgentHeartbeatUseCase.handle(new UpdateHeartbeatForm(random, Instant.now()));
            } else {
                registerAgentUseCase.handle(new RegisterAgentForm(random));
            }
        } else if (message.contains("fill")) {
            processAgentFillUseCase.handle(new ReceiveAgentFillForm(
                    random,
                    new TradeId(UUID.randomUUID()),
                    new OrderId(UUID.randomUUID()),
                    new AccountName("SomeAccount"),
                    TradingEnvironment.LIVE,
                    List.of(
                            new ContractRepresentation("TT", "EEX;TTF;Dec25")
                    ),
                    new Price(1L),
                    new Qty(5L),
                    Instant.now()
            ));
        }
    }
}
