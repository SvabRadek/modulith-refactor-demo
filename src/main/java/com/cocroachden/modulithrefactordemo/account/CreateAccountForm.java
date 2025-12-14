package com.cocroachden.modulithrefactordemo.account;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;

public record CreateAccountForm(
        String name,
        TradingEnvironment tradingEnvironment
) {
}
