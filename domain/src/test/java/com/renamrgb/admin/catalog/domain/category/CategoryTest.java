package com.renamrgb.admin.catalog.domain.category;

import com.renamrgb.admin.catalog.domain.exceptions.DomainException;
import com.renamrgb.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryTest {

    private static final ThrowsValidationHandler VALIDATION_HANDLER = new ThrowsValidationHandler();

    @Test
    void givenAValidParams_whenCallNewCategory_thenInstantiatesCategory() {
        final var expectedName = "Filme";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(VALIDATION_HANDLER));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidEmptyDescription_whenCallNewCategory_thenInstantiatesCategory() {
        final var expectedName = "Filme";
        final var expectedDescription = " ";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(VALIDATION_HANDLER));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidFalseIsActive_whenCallNewCategory_thenInstantiatesCategory() {
        final var expectedName = "Filme";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = false;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(VALIDATION_HANDLER));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertFalse(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAnInvalidNullName_whenCallNewCategory_thenShouldReceiveError() {
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(null, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(VALIDATION_HANDLER));

        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals("'name' should not be null", actualException.getErrors().get(0).message());
    }

    @Test
    void givenAnInvalidEmptyName_whenCallNewCategory_thenShouldReceiveError() {
        final var expectedName = " ";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(VALIDATION_HANDLER));

        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals("'name' should not be empty", actualException.getErrors().get(0).message());
    }

    @Test
    void givenAnInvalidNameLengthLessThan3_whenCallNewCategory_thenShouldReceiveError() {
        final var expectedName = "Fi ";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(VALIDATION_HANDLER));

        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals("'name' must be between 3 and 255", actualException.getErrors().get(0).message());
    }

    @Test
    void givenAnInvalidNameLengthMoreThan255_whenCallNewCategory_thenShouldReceiveError() {
        final var expectedName = "A".repeat(256);
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;
        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(VALIDATION_HANDLER));

        Assertions.assertEquals(1, actualException.getErrors().size());
        Assertions.assertEquals("'name' must be between 3 and 255", actualException.getErrors().get(0).message());
    }

    @Test
    void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivate() {
        final var expectedName = "Filme";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;
        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(VALIDATION_HANDLER));

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.deactivate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(VALIDATION_HANDLER));

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertFalse(actualCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidInactivateCategory_whenCallActivate_thenReturnCategoryActivated() {
        final var expectedName = "Filme";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = false;
        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(VALIDATION_HANDLER));

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        Assertions.assertFalse(aCategory.isActive());
        Assertions.assertNotNull(aCategory.getDeletedAt());

        final var actualCategory = aCategory.activate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(VALIDATION_HANDLER));

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
        final var expectedName = "Filme";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory("Fil", "Filme ", expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(VALIDATION_HANDLER));

        Assertions.assertTrue(aCategory.isActive());

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidCategory_whenCallUpdateInInactivate_thenReturnCategoryUpdated() {
        final var expectedName = "Filme";
        final var expectedDescription = "Filme description";
        final var expectedIsActive = false;

        final var aCategory = Category.newCategory("Fil", "Filme ", true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(VALIDATION_HANDLER));
        Assertions.assertTrue(aCategory.isActive());

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertFalse(actualCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidCategory_whenCallUpdateIWithInvalidParams_thenReturnCategoryUpdated() {
        final var expectedDescription = "Filme description";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory("Filmes", "Filme ", expectedIsActive);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(VALIDATION_HANDLER));

        final var createdAt = aCategory.getCreatedAt();
        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(null, expectedDescription, expectedIsActive);

        Assertions.assertEquals(actualCategory.getId(), aCategory.getId());
        Assertions.assertNull(actualCategory.getName());
        Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }
}
