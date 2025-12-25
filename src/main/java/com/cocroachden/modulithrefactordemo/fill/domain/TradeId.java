package com.cocroachden.modulithrefactordemo.fill.domain;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;

import java.util.UUID;

public class TradeId extends AbstractEntityId<UUID> {

    protected TradeId() {
    }

    public TradeId(UUID id) {
        super(id);
    }

    public static TradeId random() {
        return new TradeId(UUID.randomUUID());
    }
}
