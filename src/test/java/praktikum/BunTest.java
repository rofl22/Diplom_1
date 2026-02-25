package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для Bun")
class BunTest {

    @ParameterizedTest
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300"
    })
    @DisplayName("getName должен возвращать корректное имя")
    void getNameShouldReturnCorrectName(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @ParameterizedTest
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300"
    })
    @DisplayName("getPrice должен возвращать корректную цену")
    void getPriceShouldReturnCorrectPrice(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice());
    }
}