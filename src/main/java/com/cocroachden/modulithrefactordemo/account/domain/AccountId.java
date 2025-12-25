package com.cocroachden.modulithrefactordemo.account.domain;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;

import java.util.UUID;

public class AccountId extends AbstractEntityId<UUID> {
    public AccountId(UUID id) {
        super(id);
    }

    protected AccountId() {
    }

    public static AccountId random() {
        return new AccountId(UUID.randomUUID());
    }
}
