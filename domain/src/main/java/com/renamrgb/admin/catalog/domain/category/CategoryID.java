package com.renamrgb.admin.catalog.domain.category;

import com.renamrgb.admin.catalog.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {

    private final String value;

    private CategoryID(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CategoryID unique() {
        return from(UUID.randomUUID());
    }

    public static CategoryID from(String anId) {
        return new CategoryID(anId);
    }

    public static CategoryID from(UUID anId) {
        return new CategoryID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CategoryID that = (CategoryID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
