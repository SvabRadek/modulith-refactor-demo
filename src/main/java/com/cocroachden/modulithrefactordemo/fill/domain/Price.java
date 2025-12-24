package com.cocroachden.modulithrefactordemo.fill.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

@Embeddable
public record Price(Long value) {
    public Price {
        Assert.notNull(value, "Value cannot be null!");
    }
}
