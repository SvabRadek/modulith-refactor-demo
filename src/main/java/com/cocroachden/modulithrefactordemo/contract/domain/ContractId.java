package com.cocroachden.modulithrefactordemo.contract.domain;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record ContractId(UUID id) {
    public static ContractId random() {
        return new ContractId(UUID.randomUUID());
    }
}
