package com.cocroachden.modulithrefactordemo.account.repository.converters;

import com.cocroachden.modulithrefactordemo.account.AccountId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class AccountIdAttributeConverter implements AttributeConverter<AccountId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(AccountId attribute) {
        return attribute.getId();
    }

    @Override
    public AccountId convertToEntityAttribute(UUID dbData) {
        return new AccountId(dbData);
    }
}
