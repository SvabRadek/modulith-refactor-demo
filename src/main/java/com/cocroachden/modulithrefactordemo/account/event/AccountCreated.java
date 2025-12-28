package com.cocroachden.modulithrefactordemo.account.event;

import com.cocroachden.modulithrefactordemo.account.Account;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public class AccountCreated extends AccountChanged {
    public AccountCreated(Account account) {
        super(account);
    }
}
