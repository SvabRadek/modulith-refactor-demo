package com.cocroachden.modulithrefactordemo.account.query;

import com.cocroachden.modulithrefactordemo.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountQuery {

    private final AccountRepository accountRepository;
}
