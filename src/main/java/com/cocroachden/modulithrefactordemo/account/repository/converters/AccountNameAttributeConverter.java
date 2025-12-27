package com.cocroachden.modulithrefactordemo.account.repository.converters;

import com.cocroachden.modulithrefactordemo.account.AccountName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AccountNameAttributeConverter implements AttributeConverter<AccountName, String> {
    @Override
    public String convertToDatabaseColumn(AccountName attribute) {
        return attribute.value();
    }

    @Override
    public AccountName convertToEntityAttribute(String dbData) {
        return new AccountName(dbData);
    }
}
