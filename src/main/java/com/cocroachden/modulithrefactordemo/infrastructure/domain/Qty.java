package com.cocroachden.modulithrefactordemo.infrastructure.domain;

import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.util.Assert;

@ValueObject
public record Qty(Long value) {
    public Qty {
        Assert.notNull(value, "Value cannot be null!");
    }
}
