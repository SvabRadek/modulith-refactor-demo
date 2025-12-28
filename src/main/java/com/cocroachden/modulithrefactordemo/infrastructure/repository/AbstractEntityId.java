package com.cocroachden.modulithrefactordemo.infrastructure.repository;

import jakarta.persistence.MappedSuperclass;
import org.jmolecules.ddd.types.Identifier;

import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntityId<T extends Serializable> implements Serializable, EntityId<T> {

    private T id;

    protected AbstractEntityId() {
    }

    public AbstractEntityId(T id) {
        this.id = Objects.requireNonNull(id, "Id cannot be null!");
    }

    @Override
    public T getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass().equals(this.getClass())) {
            var other = (AbstractEntityId<?>) obj;
            return id.equals(other.getId());
        }
        return false;
    }

    @Override
    public String toString() {
        return "%s[id=%s]".formatted(getClass().getSimpleName(), getId().toString());
    }
}
