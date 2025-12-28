package com.cocroachden.modulithrefactordemo.agent.repository;

import com.cocroachden.modulithrefactordemo.agent.AgentId;
import com.cocroachden.modulithrefactordemo.agent.event.AgentHeartbeatUpdated;
import com.cocroachden.modulithrefactordemo.agent.event.AgentRegistered;
import com.cocroachden.modulithrefactordemo.agent.utils.AgentUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "agent")
@AggregateRoot
public class AgentEntity extends AbstractEntity<AgentId> {

    @EmbeddedId
    @Identity
    private AgentId id;

    private Instant lastHeartbeat;

    @Enumerated(EnumType.STRING)
    private TradingEnvironment tradingEnvironment;

    public static AgentEntity register(
            AgentId id,
            TradingEnvironment tradingEnvironment,
            Instant instant
    ) {
        var entity = new AgentEntity(id);
        entity.setTradingEnvironment(tradingEnvironment);
        entity.setLastHeartbeat(instant);
        entity.registerEvent(new AgentRegistered(AgentUtils.map(entity)));
        return entity;
    }

    private AgentEntity(AgentId id) {
        this.id = id;
    }

    public AgentEntity updateHeartbeat(Instant lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
        registerEvent(new AgentHeartbeatUpdated(AgentUtils.map(this)));
        return this;
    }
}


