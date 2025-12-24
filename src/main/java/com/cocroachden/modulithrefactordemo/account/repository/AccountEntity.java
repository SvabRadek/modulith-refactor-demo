package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.domain.AccountId;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "trading_environment"}))
public class AccountEntity extends AbstractEntity<AccountId> {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "tradingEnvironment", column = @Column(name = "trading_environment"))
    })
    private AccountId id;

    public AccountEntity(AccountId id) {
        this.id = id;
    }
}
