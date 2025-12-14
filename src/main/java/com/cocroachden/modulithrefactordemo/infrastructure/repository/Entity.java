package com.cocroachden.modulithrefactordemo.infrastructure.repository;

public interface Entity<T extends EntityId<?>> {
    T getId();
}
