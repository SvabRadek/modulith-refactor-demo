package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.domain.TradeId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TradeIdAttributeConverter implements AttributeConverter<TradeId, String> {

    @Override
    public String convertToDatabaseColumn(TradeId attribute) {
        return attribute.value();
    }

    @Override
    public TradeId convertToEntityAttribute(String dbData) {
        return new TradeId(dbData);
    }
}
