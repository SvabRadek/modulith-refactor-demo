package com.cocroachden.modulithrefactordemo.account.utils;

import com.cocroachden.modulithrefactordemo.account.domain.Account;
import com.cocroachden.modulithrefactordemo.account.repository.AccountEntity;

public class AccountUtils {
    public static Account map(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getName(),
                entity.getTradingEnvironment()
        );
    }
}
