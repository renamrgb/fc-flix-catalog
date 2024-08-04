package com.renamrgb.admin.catalog.application.category.update;

public record UṕdateCategoryCommand(
    String id,
    String name,
    String description,
    boolean isActive
) {
    public static UṕdateCategoryCommand with(
        String id,
        String name,
        String description,
        boolean isActive) {
        return new UṕdateCategoryCommand(id, name, description, isActive);
    }
}
