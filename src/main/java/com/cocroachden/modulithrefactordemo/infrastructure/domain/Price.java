package com.cocroachden.modulithrefactordemo.infrastructure.domain;

import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.util.Assert;

@ValueObject
public record Price(Long value) {
    public Price {
        Assert.notNull(value, "Value cannot be null!");
    }
}
