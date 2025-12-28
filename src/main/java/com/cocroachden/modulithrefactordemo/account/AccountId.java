package com.cocroachden.modulithrefactordemo.account;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
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
