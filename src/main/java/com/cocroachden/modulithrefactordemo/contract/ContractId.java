package com.cocroachden.modulithrefactordemo.contract;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;
import org.jmolecules.ddd.annotation.Identity;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
public class ContractId extends AbstractEntityId<UUID> {
    public ContractId(UUID id) {
        super(id);
    }

    protected ContractId() {}

    public static ContractId random() {
        return new ContractId(UUID.randomUUID());
    }
}
