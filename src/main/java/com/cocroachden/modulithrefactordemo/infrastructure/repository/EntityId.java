package com.cocroachden.modulithrefactordemo.infrastructure.repository;

import java.io.Serializable;

public interface EntityId<T> extends Serializable {
    T getId();
    String asString();
}
