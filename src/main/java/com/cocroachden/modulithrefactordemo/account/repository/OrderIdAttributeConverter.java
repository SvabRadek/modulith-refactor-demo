package com.cocroachden.modulithrefactordemo.account.repository;

import com.cocroachden.modulithrefactordemo.account.domain.OrderId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderIdAttributeConverter implements AttributeConverter<OrderId, String> {
    @Override
    public String convertToDatabaseColumn(OrderId attribute) {
        return attribute.value();
    }

    @Override
    public OrderId convertToEntityAttribute(String dbData) {
        return new OrderId(dbData);
    }
}
