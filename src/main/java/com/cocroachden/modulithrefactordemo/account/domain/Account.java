package com.cocroachden.modulithrefactordemo.account.domain;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;

public record Account(
        AccountId id,
        AccountName name,
        TradingEnvironment tradingEnvironment
) {
}
