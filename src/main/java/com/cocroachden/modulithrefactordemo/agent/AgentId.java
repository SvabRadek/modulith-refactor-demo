package com.cocroachden.modulithrefactordemo.agent;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
public class AgentId extends AbstractEntityId<UUID> {
    protected AgentId() {
        super();
    }

    public AgentId(UUID id) {
        super(id);
    }

    public static AgentId random() {
        return new AgentId(UUID.randomUUID());
    }
}
