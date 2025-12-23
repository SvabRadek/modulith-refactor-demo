package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.domain.Price;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PriceAttributeConverter implements AttributeConverter<Price, Long> {

    @Override
    public Long convertToDatabaseColumn(Price attribute) {
        return attribute.value();
    }

    @Override
    public Price convertToEntityAttribute(Long dbData) {
        return new Price(dbData);
    }
}
