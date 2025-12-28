package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.Account;
import com.cocroachden.modulithrefactordemo.account.AccountId;
import com.cocroachden.modulithrefactordemo.account.repository.AccountEntity;
import com.cocroachden.modulithrefactordemo.account.repository.AccountRepository;
import com.cocroachden.modulithrefactordemo.account.utils.AccountUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class CreateAccountUseCase {

    private final AccountRepository accountRepository;

    public Account handle(CreateAccountForm form) throws AccountAlreadyExists {
        var accountId = AccountId.random();
        if (accountRepository.existsByNameAndTradingEnvironment(form.name(), form.tradingEnvironment())) {
            throw new AccountAlreadyExists(form.name(), form.tradingEnvironment());
        }
        var newAccount = AccountEntity.create(
                accountId,
                form.name(),
                form.tradingEnvironment()
        );
        var saved = accountRepository.save(newAccount);
        return AccountUtils.map(saved);
    }

}
