package com.cocroachden.modulithrefactordemo.contract;

import org.jmolecules.ddd.annotation.ValueObject;

public record Contract(
    ContractId id,
    ContractRepresentations representations
) {
}
