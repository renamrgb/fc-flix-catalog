package com.renamrgb.admin.catalog.application.category.create;

import com.renamrgb.admin.catalog.application.UseCase;
import com.renamrgb.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
    extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
