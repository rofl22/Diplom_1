package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для Ingredient")
class IngredientTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Конструктор должен устанавливать тип")
    void constructorShouldSetType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "test", 50f);
        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("getType должен возвращать корректный тип")
    void getTypeShouldReturnCorrectType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "test", 50f);
        assertEquals(type, ingredient.getType());
    }
}