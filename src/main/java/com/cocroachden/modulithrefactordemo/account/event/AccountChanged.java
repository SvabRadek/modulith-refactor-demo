package com.cocroachden.modulithrefactordemo.account.event;

import com.cocroachden.modulithrefactordemo.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jmolecules.event.annotation.DomainEvent;

@AllArgsConstructor
@Getter
public abstract class AccountChanged {
    private final Account account;
}
