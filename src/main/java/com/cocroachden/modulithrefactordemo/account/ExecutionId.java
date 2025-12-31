package com.cocroachden.modulithrefactordemo.account;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record ExecutionId(String value) {
}
