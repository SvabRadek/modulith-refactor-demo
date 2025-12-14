package com.cocroachden.modulithrefactordemo.account.domain;

import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;

import java.time.Instant;

public record RecordedFill(
        FillId id,
        AccountId accountId,
        ContractId contractId,
        Long price,
        Long qty,
        Instant recordedAt
) {
}
