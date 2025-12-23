package com.cocroachden.modulithrefactordemo.contract.utils;

import com.cocroachden.modulithrefactordemo.contract.domain.Contract;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractEntity;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepresentationEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class ContractUtils {

    public static Contract map(ContractEntity entity) {
        return new Contract(
                entity.getId(),
                entity.getRepresentations()
        );
    }

    public static ContractRepresentation map(ContractRepresentationEntity entity) {
        return new ContractRepresentation(
                entity.format(),
                entity.representation()
        );
    }

    public static ContractRepresentationEntity map(ContractRepresentation dto) {
        return new ContractRepresentationEntity(
                dto.format(),
                dto.value()
        );
    }

    public static Set<ContractRepresentationEntity> map(ContractRepresentations dto) {
        return dto.stream().map(ContractUtils::map).collect(Collectors.toSet());
    }
}
