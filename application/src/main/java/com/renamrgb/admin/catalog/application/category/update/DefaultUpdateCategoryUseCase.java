package com.renamrgb.admin.catalog.application.category.update;

import com.renamrgb.admin.catalog.domain.category.Category;
import com.renamrgb.admin.catalog.domain.category.CategoryGateway;
import com.renamrgb.admin.catalog.domain.category.CategoryID;
import com.renamrgb.admin.catalog.domain.exceptions.DomainException;
import com.renamrgb.admin.catalog.domain.validation.Error;
import com.renamrgb.admin.catalog.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UṕdateCategoryCommand aCommand) {
        final var anId = CategoryID.from(aCommand.id());

        final var aCategory = this.categoryGateway.findById(anId)
            .orElseThrow(() -> DomainException.with(new Error("Category with ID %s was not found".formatted(anId.getValue()))));

        final var notification = Notification.create();
        aCategory
            .update(aCommand.name(), aCommand.description(), aCommand.isActive())
            .validate(notification);

        return notification.hasError() ? Left(notification) : update(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> update(Category aCategory) {
        return API.Try(() -> this.categoryGateway.update(aCategory))
            .toEither()
            .bimap(Notification::create, UpdateCategoryOutput::from);
    }
}
