package com.cocroachden.modulithrefactordemo.contract.domain;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;

import java.util.UUID;

public class ContractId extends AbstractEntityId<UUID> {
    public ContractId(UUID id) {
        super(id);
    }

    protected ContractId() {}

    public static ContractId random() {
        return new ContractId(UUID.randomUUID());
    }
}
