package com.cocroachden.modulithrefactordemo.infrastructure.domain;

import org.springframework.util.Assert;

public record Price(Long value) {
    public Price {
        Assert.notNull(value, "Value cannot be null!");
    }
}
