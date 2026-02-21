package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тесты для перечисления IngredientType")
class IngredientTypeTest {

    @Test
    @DisplayName("Перечисление должно содержать два значения: SAUCE и FILLING")
    void shouldHaveTwoValues() {
        IngredientType[] expectedValues = {IngredientType.SAUCE, IngredientType.FILLING};
        IngredientType[] actualValues = IngredientType.values();

        assertEquals(2, actualValues.length, "Должно быть 2 значения в перечислении");
        assertEquals(expectedValues[0], actualValues[0], "Первый элемент должен быть SAUCE");
        assertEquals(expectedValues[1], actualValues[1], "Второй элемент должен быть FILLING");
    }

    @Test
    @DisplayName("SAUCE должно иметь правильное строковое представление")
    void sauceShouldHaveCorrectStringRepresentation() {
        String expectedString = "SAUCE";
        String actualString = IngredientType.SAUCE.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("FILLING должно иметь правильное строковое представление")
    void fillingShouldHaveCorrectStringRepresentation() {
        String expectedString = "FILLING";
        String actualString = IngredientType.FILLING.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("valueOf() должен возвращать правильные значения для SAUCE")
    void valueOfShouldReturnSauceForValidString() {
        IngredientType expectedType = IngredientType.SAUCE;
        IngredientType actualType = IngredientType.valueOf("SAUCE");

        assertEquals(expectedType, actualType);
    }

    @Test
    @DisplayName("valueOf() должен возвращать правильные значения для FILLING")
    void valueOfShouldReturnFillingForValidString() {
        IngredientType expectedType = IngredientType.FILLING;
        IngredientType actualType = IngredientType.valueOf("FILLING");

        assertEquals(expectedType, actualType);
    }

    @Test
    @DisplayName("valueOf() должен выбрасывать IllegalArgumentException для несуществующего значения")
    void valueOfShouldThrowExceptionForInvalidString() {
        String invalidTypeName = "INVALID_TYPE";

        assertThrows(IllegalArgumentException.class,
                () -> IngredientType.valueOf(invalidTypeName),
                "Должно быть исключение для несуществующего типа");
    }
}