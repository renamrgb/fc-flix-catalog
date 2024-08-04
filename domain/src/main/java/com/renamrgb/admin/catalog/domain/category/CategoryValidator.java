package com.renamrgb.admin.catalog.domain.category;

import com.renamrgb.admin.catalog.domain.validation.Error;
import com.renamrgb.admin.catalog.domain.validation.ValidationHandler;
import com.renamrgb.admin.catalog.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;

    private CategoryValidator(final Category aCategory, final ValidationHandler handler) {
        super(handler);
        this.category = aCategory;
    }

    public static CategoryValidator with(final Category aCategory, final ValidationHandler handler) {
        return new CategoryValidator(aCategory, handler);
    }

    @Override
    public void validate() {
        if (this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
