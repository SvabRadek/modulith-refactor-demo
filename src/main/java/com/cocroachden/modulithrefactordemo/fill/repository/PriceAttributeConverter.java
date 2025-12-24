package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.fill.domain.Price;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
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
