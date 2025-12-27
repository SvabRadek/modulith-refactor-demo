package com.cocroachden.modulithrefactordemo.account.utils;

import com.cocroachden.modulithrefactordemo.account.domain.Account;
import com.cocroachden.modulithrefactordemo.fill.domain.RecordedFill;
import com.cocroachden.modulithrefactordemo.account.repository.AccountEntity;
import com.cocroachden.modulithrefactordemo.fill.repository.FillEntity;

public class AccountUtils {
    public static Account map(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getName(),
                entity.getTradingEnvironment()
        );
    }
}
