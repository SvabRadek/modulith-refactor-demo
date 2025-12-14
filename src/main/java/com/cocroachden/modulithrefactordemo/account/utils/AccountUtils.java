package com.cocroachden.modulithrefactordemo.account.utils;

import com.cocroachden.modulithrefactordemo.account.domain.Account;
import com.cocroachden.modulithrefactordemo.account.domain.RecordedFill;
import com.cocroachden.modulithrefactordemo.account.repository.AccountEntity;
import com.cocroachden.modulithrefactordemo.account.repository.FillEntity;

public class AccountUtils {

    public static Account map(AccountEntity entity) {
        return new Account(entity.getId());
    }

    public static RecordedFill map(FillEntity entity) {
        return new RecordedFill(
                entity.getId(),
                entity.getAccount().getId(),
                entity.getContractId(),
                entity.getPrice(),
                entity.getQty(),
                entity.getRecordedAt()
        );
    }

}
