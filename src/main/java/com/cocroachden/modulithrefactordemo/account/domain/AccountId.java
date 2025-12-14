package com.cocroachden.modulithrefactordemo.account.domain;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Embeddable
public record AccountId(String name, @Enumerated(EnumType.STRING) TradingEnvironment tradingEnvironment) {
    public AccountId {
        Objects.requireNonNull(name, "Account name cannot be null!");
        Objects.requireNonNull(tradingEnvironment, "Trading environment cannot be null!");
    }
}
