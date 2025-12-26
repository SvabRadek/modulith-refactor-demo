package com.cocroachden.modulithrefactordemo.account.domain;

import org.springframework.util.Assert;

public record Qty(Long value) {
    public Qty {
        Assert.notNull(value, "Value cannot be null!");
    }
}
