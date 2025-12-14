package com.cocroachden.modulithrefactordemo.contract.event;

import com.cocroachden.modulithrefactordemo.contract.domain.Contract;

public class ContractMerged extends ContractChanged {
    public ContractMerged(Contract contract) {
        super(contract);
    }
}
