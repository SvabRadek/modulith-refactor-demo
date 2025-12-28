package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.AccountId;
import com.cocroachden.modulithrefactordemo.account.AccountName;
import com.cocroachden.modulithrefactordemo.account.event.AccountCreated;
import com.cocroachden.modulithrefactordemo.account.utils.AccountUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
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
    private AccountId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name"))
    private AccountName name;
    @Enumerated(EnumType.STRING)
    private TradingEnvironment tradingEnvironment;

    private AccountEntity(AccountId id) {
        this.id = id;
    }

    public static AccountEntity create(AccountId id, AccountName name, TradingEnvironment tradingEnvironment) {
        var entity = new AccountEntity(id);
        entity.setName(name);
        entity.setTradingEnvironment(tradingEnvironment);
        entity.registerEvent(new AccountCreated(AccountUtils.map(entity)));
        return entity;
    }
}
