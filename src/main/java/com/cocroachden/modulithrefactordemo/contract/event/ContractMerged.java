package com.cocroachden.modulithrefactordemo.contract.event;

import com.cocroachden.modulithrefactordemo.contract.Contract;

public class ContractMerged extends ContractChanged {
    public ContractMerged(Contract contract) {
        super(contract);
    }
}
