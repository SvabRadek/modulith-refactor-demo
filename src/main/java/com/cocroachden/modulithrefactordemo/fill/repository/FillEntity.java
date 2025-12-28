package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.account.AccountId;
import com.cocroachden.modulithrefactordemo.fill.FillId;
import com.cocroachden.modulithrefactordemo.fill.event.FillRecorded;
import com.cocroachden.modulithrefactordemo.fill.utils.FillUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Price;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Qty;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;
import com.cocroachden.modulithrefactordemo.contract.ContractId;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.time.Instant;

@AggregateRoot
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fill", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"trade_id", "order_id"})
})
public class FillEntity extends AbstractEntity<FillId> {

    @Identity
    @EmbeddedId
    private FillId id;

    private AccountId accountId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "trade_id"))
    private ExchangeTradeId tradeId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "order_id"))
    private ExchangeOrderId orderId;

    private ContractId contractId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price"))
    private Price price;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "qty"))
    private Qty qty;

    private Instant recordedAt;

    private FillEntity(FillId id) {
        this.id = id;
    }

    public static FillEntity record(
            AccountId accountId,
            ExchangeTradeId tradeId,
            ExchangeOrderId orderId,
            ContractId contractId,
            Price price,
            Qty qty,
            Instant recordedAt
    ) {
        var entity = new FillEntity(FillId.random());
        entity.accountId = accountId;
        entity.tradeId = tradeId;
        entity.orderId = orderId;
        entity.contractId = contractId;
        entity.price = price;
        entity.qty = qty;
        entity.recordedAt = recordedAt;
        entity.registerEvent(new FillRecorded(FillUtils.map(entity)));
        return entity;
    }
}
