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
    void constructorShouldSetNameAndPrice(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName(), "Имя булочки должно совпадать с переданным в конструктор");
        assertEquals(price, bun.getPrice(), "Цена булочки должна совпадать с переданной в конструктор");
    }

    @Test
    @DisplayName("Поля name и price должны быть публично доступны")
    void publicFieldsShouldBeAccessible() {
        Bun bun = new Bun("test bun", 123.45f);

        assertEquals("test bun", bun.name, "Поле name должно быть публичным");
        assertEquals(123.45f, bun.price, "Поле price должно быть публичным");
    }

    @Test
    @DisplayName("getName() должен возвращать правильное имя")
    void getNameShouldReturnCorrectName() {
        Bun bun = new Bun("black bun", 100f);

        assertEquals("black bun", bun.getName());
    }

    @Test
    @DisplayName("getPrice() должен возвращать правильную цену")
    void getPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun("red bun", 300f);

        assertEquals(300f, bun.getPrice());
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