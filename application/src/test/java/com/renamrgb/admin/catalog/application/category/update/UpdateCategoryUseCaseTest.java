package com.renamrgb.admin.catalog.application.category.update;

import com.renamrgb.admin.catalog.domain.category.Category;
import com.renamrgb.admin.catalog.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    void givenAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId() {
        final var aCategory = Category.newCategory("Fim", null, true);

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedId = aCategory.getId();

        final var aCommand = UṕdateCategoryCommand.with(
            expectedId.getValue(),
            expectedName,
            expectedDescription,
            expectedIsActive);

        when(categoryGateway.findById(eq(expectedId))).thenReturn(Optional.of(aCategory.clone()));
        when(categoryGateway.update(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, times(1)).findById(expectedId);
        Mockito.verify(categoryGateway, times(1)).
            update(argThat(updatedCategory ->
                Objects.nonNull(updatedCategory.getId())
                    && Objects.equals(expectedName, updatedCategory.getName())
                    && Objects.equals(expectedDescription, updatedCategory.getDescription())
                    && Objects.equals(expectedIsActive, updatedCategory.isActive())
                    && Objects.equals(aCategory.getCreatedAt(), updatedCategory.getCreatedAt())
                    && aCategory.getUpdatedAt().isBefore(updatedCategory.getUpdatedAt())
                    && Objects.isNull(updatedCategory.getDeletedAt())));
    }
}
