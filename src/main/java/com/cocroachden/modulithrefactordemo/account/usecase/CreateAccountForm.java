package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.domain.AccountName;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;

import java.util.Objects;

public record CreateAccountForm(
        AccountName name,
        TradingEnvironment tradingEnvironment
) {
    public CreateAccountForm {
        Objects.requireNonNull(name, "Account name cannot be null!");
        Objects.requireNonNull(tradingEnvironment, "Trading environment cannot be null!");
    }
}
