package com.cocroachden.modulithrefactordemo.contract.domain;

public record ContractRepresentation(
        String format,
        String value
) {
    public String print() {
        return String.format("%s: %s", format, value);
    }
}
