package com.cocroachden.modulithrefactordemo.agent.messaging;

import com.cocroachden.modulithrefactordemo.agent.AgentId;
import com.cocroachden.modulithrefactordemo.agent.query.AgentQuery;
import com.cocroachden.modulithrefactordemo.agent.usecase.RegisterAgentForm;
import com.cocroachden.modulithrefactordemo.agent.usecase.RegisterAgentUseCase;
import com.cocroachden.modulithrefactordemo.agent.usecase.UpdateAgentHeartbeatUseCase;
import com.cocroachden.modulithrefactordemo.agent.usecase.UpdateHeartbeatForm;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class KafkaAgentConsumer {

    private final RegisterAgentUseCase registerAgentUseCase;
    private final UpdateAgentHeartbeatUseCase updateAgentHeartbeatUseCase;
    private final AgentQuery agentQuery;

    private final List<AgentId> agents = List.of(AgentId.random(), AgentId.random());

    @Scheduled(fixedRate = 5000)
    private void produceHb() {
        onMessage(agents.getFirst(), "heartbeat");
        onMessage(agents.getLast(), "heartbeat");
    }

    void onMessage(AgentId agentId, String message) {
        if (message.contains("heartbeat")) {
            var agent = agentQuery.findById(agentId);
            if (agent.isEmpty()) {
                registerAgentUseCase.handle(new RegisterAgentForm(agentId, TradingEnvironment.LIVE));
            } else {
                updateAgentHeartbeatUseCase.handle(new UpdateHeartbeatForm(agentId, Instant.now()));
            }
        }
    }
}
