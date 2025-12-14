package com.cocroachden.modulithrefactordemo.account.event;

import com.cocroachden.modulithrefactordemo.account.domain.Account;

public class AccountCreated extends AccountChanged {
    public AccountCreated(Account account) {
        super(account);
    }
}
