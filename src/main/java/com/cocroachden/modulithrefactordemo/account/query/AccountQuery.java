package com.cocroachden.modulithrefactordemo.account.query;

import com.cocroachden.modulithrefactordemo.account.Account;
import com.cocroachden.modulithrefactordemo.account.AccountId;
import com.cocroachden.modulithrefactordemo.account.AccountName;
import com.cocroachden.modulithrefactordemo.account.repository.AccountRepository;
import com.cocroachden.modulithrefactordemo.account.utils.AccountUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountQuery {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Optional<Account> findById(AccountId accountId) {
        return accountRepository.findById(accountId)
                .map(AccountUtils::map);
    }

    @Transactional(readOnly = true)
    public Optional<Account> findByName(AccountName accountName, TradingEnvironment tradingEnvironment) {
        return accountRepository.findByNameAndTradingEnvironment(
                accountName, tradingEnvironment
        ).map(AccountUtils::map);
    }
}
