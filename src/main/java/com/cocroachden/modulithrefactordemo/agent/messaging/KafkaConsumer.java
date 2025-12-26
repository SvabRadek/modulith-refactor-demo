package com.cocroachden.modulithrefactordemo.agent.messaging;

import com.cocroachden.modulithrefactordemo.account.domain.AccountName;
import com.cocroachden.modulithrefactordemo.account.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.agent.domain.AgentId;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentRepository;
import com.cocroachden.modulithrefactordemo.agent.usecase.*;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.account.domain.OrderId;
import com.cocroachden.modulithrefactordemo.account.domain.Price;
import com.cocroachden.modulithrefactordemo.account.domain.Qty;
import com.cocroachden.modulithrefactordemo.account.domain.TradeId;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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
    private final AgentRepository agentRepository;

    private final List<AgentId> agents = List.of(AgentId.random(), AgentId.random());

    @Scheduled(fixedRate = 5000)
    private void produceHb() {
        onMessage(agents.getFirst(), "heartbeat");
        onMessage(agents.getLast(), "heartbeat");
    }

    @Scheduled(fixedRate = 7000)
    private void produceFill() {
        onMessage(agents.getFirst(), "fill");
    }

    @Scheduled(fixedRate = 8000)
    private void produceAnotherFill() {
        onMessage(agents.getLast(), "fill");
    }

    void onMessage(AgentId agentId, String message) {
        if (message.contains("heartbeat")) {
            if (agentRepository.existsById(agentId)) {
                updateAgentHeartbeatUseCase.handle(new UpdateHeartbeatForm(agentId, Instant.now()));
            } else {
                registerAgentUseCase.handle(new RegisterAgentForm(agentId, TradingEnvironment.LIVE));
            }
        } else if (message.contains("fill")) {
            processAgentFillUseCase.handle(new ReceiveAgentFillForm(
                    agentId,
                    new TradeId(UUID.randomUUID().toString()),
                    new OrderId(UUID.randomUUID().toString()),
                    new AccountName("Account1"),
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
