package com.cocroachden.modulithrefactordemo.account.domain;

public record Account(
        AccountId id,
        AccountName name,
        TradingEnvironment tradingEnvironment
) {
}
