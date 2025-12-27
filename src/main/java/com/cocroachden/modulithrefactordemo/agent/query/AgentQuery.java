package com.cocroachden.modulithrefactordemo.agent.query;

import com.cocroachden.modulithrefactordemo.agent.domain.Agent;
import com.cocroachden.modulithrefactordemo.agent.domain.AgentId;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentRepository;
import com.cocroachden.modulithrefactordemo.agent.utils.AgentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.NamedInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@NamedInterface("query")
public class AgentQuery {

    private final AgentRepository agentRepository;

    public Optional<Agent> findById(AgentId agentId) {
        return agentRepository.findById(agentId)
                .map(AgentUtils::map);
    }

}
