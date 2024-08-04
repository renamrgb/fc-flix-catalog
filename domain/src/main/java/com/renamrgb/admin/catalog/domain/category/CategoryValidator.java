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
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final String name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int nameLength = name.trim().length();

        if (nameLength > 255 || nameLength < 3) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255"));
        }
    }
}
