package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.domain.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<AccountEntity, AccountId> {
}
