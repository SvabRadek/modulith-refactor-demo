package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.agent.domain.Agent;
import com.cocroachden.modulithrefactordemo.agent.domain.AgentId;
import com.cocroachden.modulithrefactordemo.agent.event.AgentRegistered;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentEntity;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentRepository;
import com.cocroachden.modulithrefactordemo.agent.utils.AgentUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.time.InstantSource;

@UseCase
@AllArgsConstructor
public class RegisterAgentUseCase {

    private final AgentRepository agentRepository;
    private final ApplicationEventPublisher publisher;
    private final InstantSource instantSource;

    public Agent handle(RegisterAgentForm form) {
        AgentEntity agent = new AgentEntity(AgentId.random());
        agent.setLastHeartbeat(instantSource.instant());
        var saved = agentRepository.save(agent);
        var dto = AgentUtils.map(saved);
        publisher.publishEvent(new AgentRegistered(dto));
        return dto;
    }
}
