package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для класса Ingredient")
class IngredientTest {

    @Test
    @DisplayName("Конструктор должен устанавливать тип")
    void constructorShouldSetType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    @DisplayName("Конструктор должен устанавливать имя")
    void constructorShouldSetName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals("hot sauce", ingredient.getName());
    }

    @Test
    @DisplayName("Конструктор должен устанавливать цену")
    void constructorShouldSetPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals(100f, ingredient.getPrice());
    }

    @Test
    @DisplayName("Поле type должно быть публичным")
    void typeFieldShouldBePublic() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 150f);
        assertEquals(IngredientType.FILLING, ingredient.type);
    }

    @Test
    @DisplayName("Поле name должно быть публичным")
    void nameFieldShouldBePublic() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 150f);
        assertEquals("cutlet", ingredient.name);
    }

    @Test
    @DisplayName("Поле price должно быть публичным")
    void priceFieldShouldBePublic() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 150f);
        assertEquals(150f, ingredient.price);
    }
}