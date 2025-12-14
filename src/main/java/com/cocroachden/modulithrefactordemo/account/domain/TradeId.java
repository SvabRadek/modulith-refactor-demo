package com.cocroachden.modulithrefactordemo.account.domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public record TradeId(UUID id) {
    public TradeId {
        Objects.requireNonNull(id, "Trade id cannot be null!");
    }

    public static TradeId random() {
        return new TradeId(UUID.randomUUID());
    }
}
