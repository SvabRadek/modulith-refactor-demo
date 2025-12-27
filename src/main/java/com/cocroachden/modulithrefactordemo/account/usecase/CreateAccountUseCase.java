package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.Account;
import com.cocroachden.modulithrefactordemo.account.AccountId;
import com.cocroachden.modulithrefactordemo.account.event.AccountCreated;
import com.cocroachden.modulithrefactordemo.account.repository.AccountEntity;
import com.cocroachden.modulithrefactordemo.account.repository.AccountRepository;
import com.cocroachden.modulithrefactordemo.account.utils.AccountUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
@AllArgsConstructor
public class CreateAccountUseCase {

    private final AccountRepository accountRepository;
    private final ApplicationEventPublisher publisher;

    public Account handle(CreateAccountForm form) throws AccountAlreadyExists {
        var accountId = AccountId.random();
        if (accountRepository.existsByNameAndTradingEnvironment(form.name(), form.tradingEnvironment())) {
            throw new AccountAlreadyExists(form.name(), form.tradingEnvironment());
        }
        var account = new AccountEntity(accountId);
        account.setName(form.name());
        account.setTradingEnvironment(form.tradingEnvironment());
        var saved = AccountUtils.map(accountRepository.save(account));
        publisher.publishEvent(new AccountCreated(saved));
        return saved;
    }

}
