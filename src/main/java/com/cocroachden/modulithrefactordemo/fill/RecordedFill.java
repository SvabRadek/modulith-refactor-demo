package com.cocroachden.modulithrefactordemo.fill;

import com.cocroachden.modulithrefactordemo.account.AccountId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Price;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Qty;
import com.cocroachden.modulithrefactordemo.contract.ContractId;
import org.jmolecules.ddd.annotation.ValueObject;

import java.time.Instant;

public record RecordedFill(
        FillId id,
        AccountId accountId,
        ContractId contractId,
        Price price,
        Qty qty,
        Instant recordedAt
) {
}
