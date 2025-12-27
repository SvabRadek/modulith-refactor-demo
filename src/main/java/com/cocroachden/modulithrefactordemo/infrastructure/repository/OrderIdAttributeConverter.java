package com.cocroachden.modulithrefactordemo.infrastructure.repository;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderIdAttributeConverter implements AttributeConverter<ExchangeOrderId, String> {
    @Override
    public String convertToDatabaseColumn(ExchangeOrderId attribute) {
        return attribute.value();
    }

    @Override
    public ExchangeOrderId convertToEntityAttribute(String dbData) {
        return new ExchangeOrderId(dbData);
    }
}
