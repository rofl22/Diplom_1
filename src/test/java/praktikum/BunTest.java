package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты для класса Bun")
class BunTest {

    @Test
    @DisplayName("Конструктор должен устанавливать имя")
    void constructorShouldSetName() {
        Bun bun = new Bun("black bun", 100f);
        assertEquals("black bun", bun.getName());
    }

    @Test
    @DisplayName("Конструктор должен устанавливать цену")
    void constructorShouldSetPrice() {
        Bun bun = new Bun("black bun", 100f);
        assertEquals(100f, bun.getPrice());
    }

    @Test
    @DisplayName("Поле name должно быть публичным")
    void nameFieldShouldBePublic() {
        Bun bun = new Bun("white bun", 200f);
        assertEquals("white bun", bun.name);
    }

    @Test
    @DisplayName("Поле price должно быть публичным")
    void priceFieldShouldBePublic() {
        Bun bun = new Bun("white bun", 200f);
        assertEquals(200f, bun.price);
    }
}