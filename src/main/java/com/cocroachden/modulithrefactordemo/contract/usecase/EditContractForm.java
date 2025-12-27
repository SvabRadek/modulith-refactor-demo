package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.ContractId;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;

public record EditContractForm(
        ContractId id,
        ContractRepresentations representations
) {
}
