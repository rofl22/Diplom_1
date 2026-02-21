package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для класса Ingredient")
class IngredientTest {

    @Test
    @DisplayName("Конструктор должен правильно устанавливать type, name и price")
    void constructorShouldSetTypeNameAndPrice() {
        IngredientType expectedType = IngredientType.SAUCE;
        String expectedName = "hot sauce";
        float expectedPrice = 100f;

        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        assertEquals(expectedType, ingredient.getType(), "Тип должен совпадать с переданным");
        assertEquals(expectedName, ingredient.getName(), "Имя должно совпадать с переданным");
        assertEquals(expectedPrice, ingredient.getPrice(), "Цена должна совпадать с переданной");
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Конструктор должен работать со всеми типами ингредиентов")
    void constructorShouldWorkWithAllTypes(IngredientType expectedType) {
        Ingredient ingredient = new Ingredient(expectedType, "test ingredient", 50f);

        assertEquals(expectedType, ingredient.getType(), "Тип должен быть установлен корректно");
    }

    @Test
    @DisplayName("getPrice() должен возвращать правильную цену")
    void getPriceShouldReturnCorrectPrice() {
        float expectedPrice = 150f;
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", expectedPrice);

        assertEquals(expectedPrice, ingredient.getPrice());
    }

    @Test
    @DisplayName("getName() должен возвращать правильное имя")
    void getNameShouldReturnCorrectName() {
        String expectedName = "sour cream";
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, expectedName, 200f);

        assertEquals(expectedName, ingredient.getName());
    }

    @Test
    @DisplayName("getType() должен возвращать правильный тип")
    void getTypeShouldReturnCorrectType() {
        IngredientType expectedType = IngredientType.FILLING;
        Ingredient ingredient = new Ingredient(expectedType, "dinosaur", 200f);

        assertEquals(expectedType, ingredient.getType());
    }

    @Test
    @DisplayName("Публичные поля должны быть доступны")
    void publicFieldsShouldBeAccessible() {
        IngredientType expectedType = IngredientType.SAUCE;
        String expectedName = "chili sauce";
        float expectedPrice = 300f;

        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        assertEquals(expectedType, ingredient.type, "Поле type должно быть публичным");
        assertEquals(expectedName, ingredient.name, "Поле name должно быть публичным");
        assertEquals(expectedPrice, ingredient.price, "Поле price должно быть публичным");
    }
}