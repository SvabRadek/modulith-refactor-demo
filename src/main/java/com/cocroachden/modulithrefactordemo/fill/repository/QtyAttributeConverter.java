package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.fill.domain.Qty;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class QtyAttributeConverter implements AttributeConverter<Qty, Long> {

    @Override
    public Long convertToDatabaseColumn(Qty attribute) {
        return attribute.value();
    }

    @Override
    public Qty convertToEntityAttribute(Long dbData) {
        return new Qty(dbData);
    }
}
