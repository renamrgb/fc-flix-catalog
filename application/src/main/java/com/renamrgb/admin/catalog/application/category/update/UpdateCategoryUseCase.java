package com.renamrgb.admin.catalog.application.category.update;

import com.renamrgb.admin.catalog.application.UseCase;
import com.renamrgb.admin.catalog.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
    extends UseCase<UṕdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
