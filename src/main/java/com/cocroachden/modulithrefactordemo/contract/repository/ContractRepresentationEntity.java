package com.cocroachden.modulithrefactordemo.contract.repository;

import jakarta.persistence.Embeddable;

@Embeddable
public record ContractRepresentationEntity(
        String format,
        String representation
) {
}