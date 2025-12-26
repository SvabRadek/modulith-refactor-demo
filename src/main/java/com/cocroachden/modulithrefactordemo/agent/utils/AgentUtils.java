package com.cocroachden.modulithrefactordemo.agent.utils;

import com.cocroachden.modulithrefactordemo.agent.domain.Agent;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentEntity;

public class AgentUtils {

    public static Agent map(AgentEntity entity) {
        return new Agent(
                entity.getId(),
                entity.getTradingEnvironment(),
                entity.getLastHeartbeat()
        );
    }

}
