package com.cocroachden.modulithrefactordemo.contract.event;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public class ContractMerged extends ContractChanged {
    public ContractMerged(Contract contract) {
        super(contract);
    }
}
