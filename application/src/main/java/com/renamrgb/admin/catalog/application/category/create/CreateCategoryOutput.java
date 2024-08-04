package com.renamrgb.admin.catalog.application.category.create;

import com.renamrgb.admin.catalog.domain.category.Category;
import com.renamrgb.admin.catalog.domain.category.CategoryID;

public record CreateCategoryOutput(
    CategoryID id
) {

    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }
}
