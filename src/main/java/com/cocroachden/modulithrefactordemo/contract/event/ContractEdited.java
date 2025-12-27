package com.cocroachden.modulithrefactordemo.contract.event;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public class ContractEdited extends ContractChanged {
    public ContractEdited(Contract contract) {
        super(contract);
    }
}
