package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.agent.Agent;
import com.cocroachden.modulithrefactordemo.agent.event.AgentHeartbeatUpdated;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentRepository;
import com.cocroachden.modulithrefactordemo.agent.utils.AgentUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
@AllArgsConstructor
public class UpdateAgentHeartbeatUseCase {

    private final AgentRepository agentRepository;
    private final ApplicationEventPublisher publisher;

    public Agent handle(UpdateHeartbeatForm form) {
        var agent = agentRepository.findById(form.agentId());
        if (agent.isEmpty()) {
            throw new AgentNotFoundException(form.agentId());
        }
        agent.get().setLastHeartbeat(form.instant());
        var updated = agentRepository.save(agent.get());
        var dto = AgentUtils.map(updated);
        publisher.publishEvent(new AgentHeartbeatUpdated(dto));
        return dto;
    }

}
