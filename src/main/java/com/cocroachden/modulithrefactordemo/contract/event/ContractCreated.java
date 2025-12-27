package com.cocroachden.modulithrefactordemo.contract.event;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public class ContractCreated extends ContractChanged {
    public ContractCreated(Contract contract) {
        super(contract);
    }
}
