package com.cocroachden.modulithrefactordemo.contract;

import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.util.Assert;

public record ContractRepresentation(
        String format,
        String value
) {

    public ContractRepresentation {
        Assert.notNull(format, "Format cannot be null!");
        Assert.notNull(value, "Value cannot be null!");
    }

    public String print() {
        return String.format("%s: %s", format, value);
    }
}
