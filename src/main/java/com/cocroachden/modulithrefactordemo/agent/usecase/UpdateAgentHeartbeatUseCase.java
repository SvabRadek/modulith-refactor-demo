package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.agent.Agent;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentRepository;
import com.cocroachden.modulithrefactordemo.agent.utils.AgentUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class UpdateAgentHeartbeatUseCase {

    private final AgentRepository agentRepository;

    public Agent handle(UpdateHeartbeatCommand command) {
        return agentRepository.findById(command.agentId())
                .map(entity -> entity.updateHeartbeat(command.instant()))
                .map(agentRepository::save)
                .map(AgentUtils::map)
                .orElseThrow(() -> new AgentNotFoundException(command.agentId()));
    }

}
