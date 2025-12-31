package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.AccountName;
import com.cocroachden.modulithrefactordemo.account.ExecutionId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import jakarta.annotation.Nullable;

import java.util.Objects;

public record CreateAccountCommand(
        AccountName name,
        TradingEnvironment tradingEnvironment,
        @Nullable ExecutionId executionId
) {
    public CreateAccountCommand {
        Objects.requireNonNull(name, "Account name cannot be null!");
        Objects.requireNonNull(tradingEnvironment, "Trading environment cannot be null!");
    }
}
