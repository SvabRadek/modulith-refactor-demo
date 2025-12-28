package com.cocroachden.modulithrefactordemo.infrastructure.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record ExchangeTradeId(String value) {
}
