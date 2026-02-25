package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для IngredientType")
class IngredientTypeTest {

    @Test
    @DisplayName("Должно быть 2 значения в enum")
    void shouldContainTwoValues() {
        assertEquals(2, IngredientType.values().length);
    }

    @Test
    @DisplayName("Первое значение должно быть SAUCE")
    void firstValueShouldBeSauce() {
        assertEquals(IngredientType.SAUCE, IngredientType.values()[0]);
    }

    @Test
    @DisplayName("Второе значение должно быть FILLING")
    void secondValueShouldBeFilling() {
        assertEquals(IngredientType.FILLING, IngredientType.values()[1]);
    }

    @Test
    @DisplayName("valueOf должен возвращать SAUCE")
    void valueOfShouldReturnSauce() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    @DisplayName("valueOf должен выбрасывать исключение")
    void valueOfShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> IngredientType.valueOf("INVALID"));
    }
}