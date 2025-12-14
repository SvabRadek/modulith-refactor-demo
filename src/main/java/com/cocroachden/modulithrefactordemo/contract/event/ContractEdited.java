package com.cocroachden.modulithrefactordemo.contract.event;

import com.cocroachden.modulithrefactordemo.contract.domain.Contract;

public class ContractEdited extends ContractChanged {
    public ContractEdited(Contract contract) {
        super(contract);
    }
}
