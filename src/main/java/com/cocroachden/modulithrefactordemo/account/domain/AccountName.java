package com.cocroachden.modulithrefactordemo.account.domain;

import org.jspecify.annotations.NonNull;
import org.springframework.util.Assert;

public record AccountName(String value) {
    public AccountName {
        Assert.notNull(value, "Name cannot be null!");
        Assert.hasLength(value, "Name cannot be empty!");
    }

    public static AccountName of(String name) {
        return new AccountName(name);
    }

    @Override
    public @NonNull String toString() {
        return value;
    }
}
