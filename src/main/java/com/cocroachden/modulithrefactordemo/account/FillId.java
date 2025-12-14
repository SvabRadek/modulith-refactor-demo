package com.cocroachden.modulithrefactordemo.account;

import java.util.Objects;
import java.util.UUID;

public record FillId(UUID id) {
    public FillId {
        Objects.requireNonNull(id, "Id cannot be null!");
    }
    public static FillId random() {
        return new FillId(UUID.randomUUID());
    }
}
