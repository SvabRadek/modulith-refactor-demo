package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.fill.domain.TradeId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class TradeIdAttributeConverter implements AttributeConverter<TradeId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(TradeId attribute) {
        return attribute.getId();
    }

    @Override
    public TradeId convertToEntityAttribute(UUID dbData) {
        return new TradeId(dbData);
    }
}
