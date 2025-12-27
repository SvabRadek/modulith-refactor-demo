package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.AccountName;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;

public class AccountAlreadyExists extends RuntimeException {
    public AccountAlreadyExists(AccountName accountName, TradingEnvironment tradingEnvironment) {
        super("Account [%s:%s] already exists!".formatted(accountName, tradingEnvironment.name()));
    }
}
