package com.cocroachden.modulithrefactordemo.account.domain;

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
