package com.cocroachden.modulithrefactordemo.account.domain;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;

import java.util.UUID;

public class FillId extends AbstractEntityId<UUID> {

    public FillId(UUID id) {
        super(id);
    }

    protected FillId() {}

    public static FillId random() {
        return new FillId(UUID.randomUUID());
    }
}
