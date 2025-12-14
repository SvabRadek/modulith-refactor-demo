package com.cocroachden.modulithrefactordemo.account;

import com.cocroachden.modulithrefactordemo.contract.ContractId;

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
