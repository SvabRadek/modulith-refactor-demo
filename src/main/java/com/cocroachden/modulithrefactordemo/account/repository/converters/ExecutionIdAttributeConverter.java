package com.cocroachden.modulithrefactordemo.account.repository.converters;

import com.cocroachden.modulithrefactordemo.account.ExecutionId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ExecutionIdAttributeConverter implements AttributeConverter<ExecutionId, String> {
    @Override
    public String convertToDatabaseColumn(ExecutionId attribute) {
        return attribute.value();
    }

    @Override
    public ExecutionId convertToEntityAttribute(String dbData) {
        return new ExecutionId(dbData);
    }
}
