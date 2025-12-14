package com.cocroachden.modulithrefactordemo.account.event;

import com.cocroachden.modulithrefactordemo.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AccountChanged {
    private final Account account;
}
