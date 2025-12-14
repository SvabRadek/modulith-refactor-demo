package com.cocroachden.modulithrefactordemo.account.event;

import com.cocroachden.modulithrefactordemo.account.Account;

public class AccountCreated extends AccountChanged {
    public AccountCreated(Account account) {
        super(account);
    }
}
