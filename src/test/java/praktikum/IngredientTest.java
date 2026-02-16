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
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);

        assertEquals(IngredientType.SAUCE, ingredient.getType(), "Тип должен совпадать с переданным");
        assertEquals("hot sauce", ingredient.getName(), "Имя должно совпадать с переданным");
        assertEquals(100f, ingredient.getPrice(), "Цена должна совпадать с переданной");
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Конструктор должен работать со всеми типами ингредиентов")
    void constructorShouldWorkWithAllTypes(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "test ingredient", 50f);

        assertEquals(type, ingredient.getType(), "Тип должен быть установлен корректно");
    }

    @Test
    @DisplayName("getPrice() должен возвращать правильную цену")
    void getPriceShouldReturnCorrectPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 150f);

        assertEquals(150f, ingredient.getPrice());
    }

    @Test
    @DisplayName("getName() должен возвращать правильное имя")
    void getNameShouldReturnCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "sour cream", 200f);

        assertEquals("sour cream", ingredient.getName());
    }

    @Test
    @DisplayName("getType() должен возвращать правильный тип")
    void getTypeShouldReturnCorrectType() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "dinosaur", 200f);

        assertEquals(IngredientType.FILLING, ingredient.getType());
    }

    @Test
    @DisplayName("Публичные поля должны быть доступны")
    void publicFieldsShouldBeAccessible() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "chili sauce", 300f);

        assertEquals(IngredientType.SAUCE, ingredient.type, "Поле type должно быть публичным");
        assertEquals("chili sauce", ingredient.name, "Поле name должно быть публичным");
        assertEquals(300f, ingredient.price, "Поле price должно быть публичным");
    }
}