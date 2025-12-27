package com.cocroachden.modulithrefactordemo.fill.domain;

import com.cocroachden.modulithrefactordemo.account.domain.AccountId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Price;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Qty;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;

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
