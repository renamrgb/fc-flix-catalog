package com.renamrgb.admin.catalog.application.category.create;

import com.renamrgb.admin.catalog.domain.category.Category;
import com.renamrgb.admin.catalog.domain.category.CategoryGateway;
import com.renamrgb.admin.catalog.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Try;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var aCategory = Category.newCategory(aCommand.name(), aCommand.description(), aCommand.isActive());
        final var notification = Notification.create();
        aCategory.validate(notification);
        return notification.hasError() ? API.Left(notification) : create(aCategory);
    }

    private Either<Notification, CreateCategoryOutput> create(final Category aCategory) {
        return Try(() -> this.categoryGateway.create(aCategory))
            .toEither()
            .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
