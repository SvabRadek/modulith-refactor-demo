package com.cocroachden.modulithrefactordemo.fill.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

@Embeddable
public record Qty(Long value) {
    public Qty {
        Assert.notNull(value, "Value cannot be null!");
    }
}
