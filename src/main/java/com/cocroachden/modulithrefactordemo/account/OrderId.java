package com.cocroachden.modulithrefactordemo.account;

import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public record OrderId(UUID id) {
    public OrderId {
        Objects.requireNonNull(id, "Order id cannot be null!");
    }
    public static OrderId random() {
        return new OrderId(UUID.randomUUID());
    }
}
