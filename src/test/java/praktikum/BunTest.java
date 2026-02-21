package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для класса Bun")
class BunTest {

    @ParameterizedTest(name = "Булочка с названием {0} и ценой {1}")
    @MethodSource("provideBunData")
    @DisplayName("Конструктор должен правильно устанавливать name и price")
    void constructorShouldSetNameAndPrice(String expectedName, float expectedPrice) {
        Bun bun = new Bun(expectedName, expectedPrice);

        assertEquals(expectedName, bun.getName(), "Имя булочки должно совпадать с переданным в конструктор");
        assertEquals(expectedPrice, bun.getPrice(), "Цена булочки должна совпадать с переданной в конструктор");
    }

    @Test
    @DisplayName("Поля name и price должны быть публично доступны")
    void publicFieldsShouldBeAccessible() {
        String expectedName = "test bun";
        float expectedPrice = 123.45f;
        Bun bun = new Bun(expectedName, expectedPrice);

        assertEquals(expectedName, bun.name, "Поле name должно быть публичным");
        assertEquals(expectedPrice, bun.price, "Поле price должно быть публичным");
    }

    @Test
    @DisplayName("getName() должен возвращать правильное имя")
    void getNameShouldReturnCorrectName() {
        String expectedName = "black bun";
        Bun bun = new Bun(expectedName, 100f);

        assertEquals(expectedName, bun.getName());
    }

    @Test
    @DisplayName("getPrice() должен возвращать правильную цену")
    void getPriceShouldReturnCorrectPrice() {
        float expectedPrice = 300f;
        Bun bun = new Bun("red bun", expectedPrice);

        assertEquals(expectedPrice, bun.getPrice());
    }

    private static Stream<Arguments> provideBunData() {
        return Stream.of(
                Arguments.of("black bun", 100f),
                Arguments.of("white bun", 200f),
                Arguments.of("red bun", 300f),
                Arguments.of("", 0f),
                Arguments.of("special bun", 999.99f)
        );
    }
}