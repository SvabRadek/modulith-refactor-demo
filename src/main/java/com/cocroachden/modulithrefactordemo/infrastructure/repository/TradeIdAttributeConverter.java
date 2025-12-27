package com.cocroachden.modulithrefactordemo.infrastructure.repository;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TradeIdAttributeConverter implements AttributeConverter<ExchangeTradeId, String> {

    @Override
    public String convertToDatabaseColumn(ExchangeTradeId attribute) {
        return attribute.value();
    }

    @Override
    public ExchangeTradeId convertToEntityAttribute(String dbData) {
        return new ExchangeTradeId(dbData);
    }
}
