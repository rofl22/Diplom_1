package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Тесты для IngredientType")
class IngredientTypeTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Enum должен содержать значения")
    void enumShouldContainValues(IngredientType type) {
        assertNotNull(type);
    }
}