package com.renamrgb.admin.catalog.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UseCaseTest {

    @Test
    public void testCreateUseCase() {
        UseCase useCase = new UseCase();
        Assertions.assertNotNull(useCase);
        Assertions.assertNotNull(useCase.execute());
    }
}