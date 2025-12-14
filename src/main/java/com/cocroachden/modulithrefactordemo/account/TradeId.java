package com.cocroachden.modulithrefactordemo.account;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record TradeId(UUID id) {
    public static TradeId random() {
        return new TradeId(UUID.randomUUID());
    }
}
