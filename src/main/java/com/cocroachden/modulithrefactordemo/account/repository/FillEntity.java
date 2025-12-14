package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.domain.FillId;
import com.cocroachden.modulithrefactordemo.account.domain.OrderId;
import com.cocroachden.modulithrefactordemo.account.domain.TradeId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "account_id"),
            @JoinColumn(name = "trading_environment")
    })
    private AccountEntity account;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "trade_id"))
    private TradeId tradeId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "order_id"))
    private OrderId orderId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "contract_id"))
    private ContractId contractId;

    private Long price;

    private Long qty;

    private Instant recordedAt;
}
