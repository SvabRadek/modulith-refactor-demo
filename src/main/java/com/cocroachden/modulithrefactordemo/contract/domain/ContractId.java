package com.cocroachden.modulithrefactordemo.contract.domain;

import java.util.UUID;

public record ContractId(UUID id) {
    public static ContractId random() {
        return new ContractId(UUID.randomUUID());
    }
}
