package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тесты для перечисления IngredientType")
class IngredientTypeTest {

    @Test
    @DisplayName("Перечисление должно содержать два значения: SAUCE и FILLING")
    void shouldHaveTwoValues() {
        IngredientType[] values = IngredientType.values();

        assertEquals(2, values.length, "Должно быть 2 значения в перечислении");
        assertTrue(containsValue(values, IngredientType.SAUCE), "Должен присутствовать SAUCE");
        assertTrue(containsValue(values, IngredientType.FILLING), "Должен присутствовать FILLING");
    }

    @Test
    @DisplayName("SAUCE должно иметь правильное строковое представление")
    void sauceShouldHaveCorrectStringRepresentation() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
    }

    @Test
    @DisplayName("FILLING должно иметь правильное строковое представление")
    void fillingShouldHaveCorrectStringRepresentation() {
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }

    @Test
    @DisplayName("valueOf() должен возвращать правильные значения")
    void valueOfShouldReturnCorrectValues() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    private boolean containsValue(IngredientType[] values, IngredientType target) {
        for (IngredientType value : values) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }
}