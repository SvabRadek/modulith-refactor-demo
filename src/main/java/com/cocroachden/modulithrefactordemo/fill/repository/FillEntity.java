package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.account.domain.AccountId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;
import com.cocroachden.modulithrefactordemo.fill.domain.*;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fill", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"trade_id", "order_id"})
})
public class FillEntity extends AbstractEntity<FillId> {

    @EmbeddedId
    private FillId id;

    public FillEntity(FillId id) {
        this.id = id;
    }

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "account_name")),
            @AttributeOverride(name = "tradingEnvironment", column = @Column(name = "trading_environment"))
    })
    private AccountId account;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "trade_id"))
    private TradeId tradeId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "order_id"))
    private OrderId orderId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "contract_id"))
    private ContractId contractId;

    @Convert(converter = PriceAttributeConverter.class)
    private Price price;

    @Convert(converter = QtyAttributeConverter.class)
    private Qty qty;

    private Instant recordedAt;
}
