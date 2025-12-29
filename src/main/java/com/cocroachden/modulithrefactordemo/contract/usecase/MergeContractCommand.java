package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;

public record MergeContractCommand(
        ContractRepresentations representations
) {
}
