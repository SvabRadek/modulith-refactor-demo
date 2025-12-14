package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.domain.AccountId;

public class AccountDoesNotExistsException extends RuntimeException {
    public AccountDoesNotExistsException(AccountId accountId) {
        super("Account [%s:%s] does not exists!".formatted(accountId.name(), accountId.tradingEnvironment().name()));
    }
}
