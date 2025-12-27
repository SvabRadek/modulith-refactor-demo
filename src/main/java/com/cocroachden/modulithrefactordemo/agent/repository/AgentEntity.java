package com.cocroachden.modulithrefactordemo.agent.repository;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.agent.AgentId;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "agent")
public class AgentEntity extends AbstractEntity<AgentId> {

    @EmbeddedId
    private AgentId id;

    private Instant lastHeartbeat;

    @Enumerated(EnumType.STRING)
    private TradingEnvironment tradingEnvironment;

    public AgentEntity(AgentId id) {
        this.id = id;
    }
}


