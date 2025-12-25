package com.cocroachden.modulithrefactordemo.fill.domain;

import org.springframework.util.Assert;

public record Price(Long value) {
    public Price {
        Assert.notNull(value, "Value cannot be null!");
    }
}
