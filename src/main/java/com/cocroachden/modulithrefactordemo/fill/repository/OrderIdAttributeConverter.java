package com.cocroachden.modulithrefactordemo.fill.repository;

import com.cocroachden.modulithrefactordemo.fill.domain.OrderId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class OrderIdAttributeConverter implements AttributeConverter<OrderId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(OrderId attribute) {
        return attribute.getId();
    }

    @Override
    public OrderId convertToEntityAttribute(UUID dbData) {
        return new OrderId(dbData);
    }
}
