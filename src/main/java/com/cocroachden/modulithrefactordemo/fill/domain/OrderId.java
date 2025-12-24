package com.cocroachden.modulithrefactordemo.fill.domain;

import java.util.Objects;
import java.util.UUID;

public record OrderId(UUID id) {
    public OrderId {
        Objects.requireNonNull(id, "Order id cannot be null!");
    }

    public static OrderId random() {
        return new OrderId(UUID.randomUUID());
    }
}
