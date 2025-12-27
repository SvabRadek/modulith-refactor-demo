package com.cocroachden.modulithrefactordemo.fill;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
public class FillId extends AbstractEntityId<UUID> {

    public FillId(UUID id) {
        super(id);
    }

    protected FillId() {
    }

    public static FillId random() {
        return new FillId(UUID.randomUUID());
    }
}
