package com.cocroachden.modulithrefactordemo.account;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;

import java.util.UUID;

public class FillId extends AbstractEntityId<UUID> {
    protected FillId() {
    }

    public FillId(UUID id) {
        super(id);
    }

    public static FillId random() {
        return new FillId(UUID.randomUUID());
    }
}
