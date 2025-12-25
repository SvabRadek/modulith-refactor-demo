package com.cocroachden.modulithrefactordemo.fill.domain;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;

import java.util.UUID;

public class OrderId extends AbstractEntityId<UUID> {

    public OrderId(UUID id) {
        super(id);
    }

    protected OrderId() {
    }

    public static OrderId random() {
        return new OrderId(UUID.randomUUID());
    }
}
