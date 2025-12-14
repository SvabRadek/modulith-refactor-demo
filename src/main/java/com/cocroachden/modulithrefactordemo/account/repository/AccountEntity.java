package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.AccountId;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "accounts", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "trading_environment"}))
public class AccountEntity extends AbstractEntity<AccountId> {

    @EmbeddedId
    private AccountId id;

    public AccountEntity(AccountId id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<FillEntity> fills;
}
