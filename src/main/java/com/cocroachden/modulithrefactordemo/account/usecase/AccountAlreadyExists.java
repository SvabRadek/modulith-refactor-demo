package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;

public class AccountAlreadyExists extends RuntimeException {
    public AccountAlreadyExists(String accountName, TradingEnvironment tradingEnvironment) {
        super("Account [%s:%s] already exists!".formatted(accountName, tradingEnvironment.name()));
    }
}
