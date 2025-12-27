package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.fill.FillId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class FillIdAttributeConverter implements AttributeConverter<FillId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(FillId attribute) {
        return attribute.getId();
    }

    @Override
    public FillId convertToEntityAttribute(UUID dbData) {
        return new FillId(dbData);
    }
}
