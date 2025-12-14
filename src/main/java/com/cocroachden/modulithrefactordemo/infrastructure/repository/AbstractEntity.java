package com.cocroachden.modulithrefactordemo.infrastructure.repository;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity<ID> implements Persistable<ID> {

    @Transient
    private boolean isNew = true;

    @PostPersist
    @PostLoad
    protected void markNotNew() {
        isNew = false;
    }

    @Override
    public abstract ID getId();

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public final boolean equals(Object obj) {
        boolean result = false;

        if (this == obj) {
            result = true;
        } else if (obj == null) {
            return false;
        } else if (obj.getClass().equals(getClass())) {
            AbstractEntity<?> other = (AbstractEntity<?>) obj;
            result = Objects.equals(getId(), other.getId());
        }

        return result;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s]", getClass().getSimpleName(), getId());
    }
}
