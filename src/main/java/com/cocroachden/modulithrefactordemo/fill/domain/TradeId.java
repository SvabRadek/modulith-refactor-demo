package com.cocroachden.modulithrefactordemo.fill.domain;

import java.util.Objects;
import java.util.UUID;

public record TradeId(UUID id) {
    public TradeId {
        Objects.requireNonNull(id, "Trade id cannot be null!");
    }

    public static TradeId random() {
        return new TradeId(UUID.randomUUID());
    }
}
