package com.cocroachden.modulithrefactordemo.contract.utils;

import com.cocroachden.modulithrefactordemo.contract.domain.Contract;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractEntity;

public class ContractUtils {

    public static Contract map(ContractEntity entity) {
        return new Contract(
                entity.getId(),
                entity.getRepresentations()
        );
    }
}
