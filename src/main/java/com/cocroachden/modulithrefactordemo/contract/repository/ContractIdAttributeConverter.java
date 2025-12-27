package com.cocroachden.modulithrefactordemo.contract.repository;

import com.cocroachden.modulithrefactordemo.contract.ContractId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class ContractIdAttributeConverter implements AttributeConverter<ContractId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(ContractId attribute) {
        return attribute.getId();
    }

    @Override
    public ContractId convertToEntityAttribute(UUID dbData) {
        return new ContractId(dbData);
    }
}
