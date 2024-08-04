package com.renamrgb.admin.catalog.domain;


import java.util.Objects;

public abstract class Entity<ID extends Identifier> {

    protected final ID id;

    protected Entity(ID id) {
        this.id = Objects.requireNonNull(id, "'id' should not be null");
    }

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Entity<?> entity = (Entity<?>) o;
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
