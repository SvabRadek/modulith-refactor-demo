package com.cocroachden.modulithrefactordemo.account;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record OrderId(UUID id) {
    public static OrderId random() {
        return new OrderId(UUID.randomUUID());
    }
}
