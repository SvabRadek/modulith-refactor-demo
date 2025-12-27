package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.domain.AccountId;
import com.cocroachden.modulithrefactordemo.account.domain.AccountName;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, AccountId> {
    @Transactional(readOnly = true)
    Optional<AccountEntity> findByNameAndTradingEnvironment(AccountName name, TradingEnvironment tradingEnvironment);

    boolean existsByNameAndTradingEnvironment(AccountName name, TradingEnvironment tradingEnvironment);
}
