package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.ContractId;

public class ContractNotFound extends RuntimeException {
    public ContractNotFound(ContractId contractId) {
        super("Contract %s not found!".formatted(contractId.getId()));
    }
}
