package com.cocroachden.modulithrefactordemo.contract.event;

import com.cocroachden.modulithrefactordemo.contract.ContractId;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record ContractDeleted(ContractId contractId) {
}
