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

    public Account handle(CreateAccountCommand command) throws AccountAlreadyExists {
        var accountId = AccountId.random();
        if (accountRepository.existsByNameAndTradingEnvironment(command.name(), command.tradingEnvironment())) {
            throw new AccountAlreadyExists(command.name(), command.tradingEnvironment());
        }
        var newAccount = AccountEntity.create(
                accountId,
                command.name(),
                command.tradingEnvironment(),
                command.executionId()
        );
        var saved = accountRepository.save(newAccount);
        return AccountUtils.map(saved);
    }

}
