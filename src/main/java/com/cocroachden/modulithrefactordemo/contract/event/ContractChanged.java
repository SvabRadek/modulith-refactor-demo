package com.cocroachden.modulithrefactordemo.contract.event;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class ContractChanged {
    private final Contract contract;
}
