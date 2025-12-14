package com.cocroachden.modulithrefactordemo.account;

import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntityId;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.util.Objects;

@Embeddable
public record AccountId(String name, TradingEnvironment tradingEnvironment) { }
