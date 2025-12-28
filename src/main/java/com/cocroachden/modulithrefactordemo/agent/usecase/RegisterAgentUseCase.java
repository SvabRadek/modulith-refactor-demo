package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.agent.Agent;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentEntity;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentRepository;
import com.cocroachden.modulithrefactordemo.agent.utils.AgentUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;

import java.time.InstantSource;

@UseCase
@AllArgsConstructor
public class RegisterAgentUseCase {

    private final AgentRepository agentRepository;
    private final InstantSource instantSource;

    public Agent handle(RegisterAgentForm form) {
        var saved = agentRepository.save(AgentEntity.register(
                form.agentId(),
                form.tradingEnvironment(),
                instantSource.instant()
        ));
        return AgentUtils.map(saved);
    }
}
