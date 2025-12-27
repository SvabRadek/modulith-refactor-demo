package com.cocroachden.modulithrefactordemo.agent.messaging;

import com.cocroachden.modulithrefactordemo.agent.AgentId;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentRepository;
import com.cocroachden.modulithrefactordemo.agent.usecase.*;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private final RegisterAgentUseCase registerAgentUseCase;
    private final UpdateAgentHeartbeatUseCase updateAgentHeartbeatUseCase;
    private final AgentRepository agentRepository;

    private final List<AgentId> agents = List.of(AgentId.random(), AgentId.random());

    @Scheduled(fixedRate = 5000)
    private void produceHb() {
        onMessage(agents.getFirst(), "heartbeat");
        onMessage(agents.getLast(), "heartbeat");
    }

    void onMessage(AgentId agentId, String message) {
        if (message.contains("heartbeat")) {
            if (agentRepository.existsById(agentId)) {
                updateAgentHeartbeatUseCase.handle(new UpdateHeartbeatForm(agentId, Instant.now()));
            } else {
                registerAgentUseCase.handle(new RegisterAgentForm(agentId, TradingEnvironment.LIVE));
            }
        }
    }
}
