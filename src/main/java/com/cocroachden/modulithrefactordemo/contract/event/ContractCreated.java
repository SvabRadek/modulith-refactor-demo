package com.cocroachden.modulithrefactordemo.contract.event;

import com.cocroachden.modulithrefactordemo.contract.Contract;

public class ContractCreated extends ContractChanged {
    public ContractCreated(Contract contract) {
        super(contract);
    }
}
