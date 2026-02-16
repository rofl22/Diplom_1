package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для класса Burger")
class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("setBuns() должен устанавливать булочку")
    void setBunsShouldSetBun() {
        burger.setBuns(mockBun);

        assertEquals(mockBun, burger.bun, "Булочка должна быть установлена");
    }

    @Test
    @DisplayName("addIngredient() должен добавлять ингредиент в список")
    void addIngredientShouldAddIngredient() {
        burger.addIngredient(mockIngredient1);

        assertEquals(1, burger.ingredients.size(), "Должен быть один ингредиент");
        assertEquals(mockIngredient1, burger.ingredients.get(0), "Добавленный ингредиент должен быть в списке");
    }

    @Test
    @DisplayName("removeIngredient() должен удалять ингредиент по индексу")
    void removeIngredientShouldRemoveIngredientAtIndex() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size(), "Должен остаться один ингредиент");
        assertEquals(mockIngredient2, burger.ingredients.get(0), "Должен остаться второй ингредиент");
    }

    @Test
    @DisplayName("moveIngredient() должен перемещать ингредиент на новую позицию")
    void moveIngredientShouldMoveIngredientToNewIndex() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(2, 0);

        assertEquals(mockIngredient3, burger.ingredients.get(0), "Ингредиент с индексом 2 должен быть на позиции 0");
        assertEquals(mockIngredient1, burger.ingredients.get(1), "Ингредиент с индексом 0 должен быть на позиции 1");
        assertEquals(mockIngredient2, burger.ingredients.get(2), "Ингредиент с индексом 1 должен быть на позиции 2");
    }

    @Test
    @DisplayName("getPrice() должен корректно рассчитывать цену с моками")
    void getPriceShouldCalculateCorrectPriceWithMocks() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getPrice()).thenReturn(50f);
        when(mockIngredient2.getPrice()).thenReturn(75f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 100f * 2 + 50f + 75f; // 325
        assertEquals(expectedPrice, burger.getPrice(), 0.001, "Цена должна быть рассчитана корректно");

        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    @DisplayName("getPrice() с пустым списком ингредиентов")
    void getPriceWithEmptyIngredients() {
        when(mockBun.getPrice()).thenReturn(150f);

        burger.setBuns(mockBun);

        float expectedPrice = 150f * 2; // 300
        assertEquals(expectedPrice, burger.getPrice(), 0.001, "Цена должна быть равна удвоенной цене булочки");
    }

    @ParameterizedTest
    @ValueSource(strings = {"black bun", "white bun", "red bun"})
    @DisplayName("getReceipt() должен формировать корректный чек")
    void getReceiptShouldReturnCorrectReceipt(String bunName) {
        Bun realBun = new Bun(bunName, 100f);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);
        Ingredient filling = new Ingredient(IngredientType.FILLING, "cutlet", 75f);

        burger.setBuns(realBun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();
        float expectedPrice = 100f * 2 + 50f + 75f;

        String expectedHeader = String.format("(==== %s ====)%n", bunName);
        String expectedSauceLine = String.format("= %s %s =%n", "sauce", "hot sauce");
        String expectedFillingLine = String.format("= %s %s =%n", "filling", "cutlet");
        String expectedFooter = String.format("(==== %s ====)%n", bunName);
        String expectedPriceLine = String.format("%nPrice: %f%n", expectedPrice);

        assertTrue(receipt.startsWith(expectedHeader), "Чек должен начинаться с верхней булочки");
        assertTrue(receipt.contains(expectedSauceLine), "Чек должен содержать соус");
        assertTrue(receipt.contains(expectedFillingLine), "Чек должен содержать начинку");
        assertTrue(receipt.contains(expectedFooter), "Чек должен содержать нижнюю булочку");
        assertTrue(receipt.endsWith(expectedPriceLine), "Чек должен заканчиваться ценой");
    }

    @Test
    @DisplayName("getReceipt() с пустым бургером")
    void getReceiptWithEmptyBurger() {
        Bun realBun = new Bun("test bun", 100f);
        burger.setBuns(realBun);

        String receipt = burger.getReceipt();
        String expectedHeader = String.format("(==== %s ====)%n", "test bun");
        String expectedFooter = String.format("(==== %s ====)%n", "test bun");
        float expectedPrice = 200f;

        assertTrue(receipt.startsWith(expectedHeader));
        assertTrue(receipt.contains(expectedFooter));
        assertTrue(receipt.contains(String.format("%nPrice: %f%n", expectedPrice)));
    }

    @Test
    @DisplayName("Тест с использованием реальных объектов из Database")
    void testWithRealDatabaseObjects() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();

        burger.setBuns(buns.get(0)); // black bun
        burger.addIngredient(ingredients.get(1)); // sour cream
        burger.addIngredient(ingredients.get(4)); // dinosaur

        assertEquals(buns.get(0), burger.bun);
        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredients.get(1), burger.ingredients.get(0));
        assertEquals(ingredients.get(4), burger.ingredients.get(1));

        float expectedPrice = 100f * 2 + 200f + 200f; // 600
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Тест всех методов Burger с моками (исправленная версия)")
    void testAllBurgerMethodsWithMocks() {
        // Настраиваем только те моки, которые реально используются
        when(mockBun.getName()).thenReturn("mock bun");
        when(mockBun.getPrice()).thenReturn(50f);
        when(mockIngredient1.getName()).thenReturn("mock sauce");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        assertEquals(mockBun, burger.bun);
        assertEquals(1, burger.ingredients.size());

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("mock bun"));
        assertTrue(receipt.contains("mock sauce"));

        verify(mockBun, atLeastOnce()).getName();
        verify(mockIngredient1, atLeastOnce()).getType();
        verify(mockIngredient1, atLeastOnce()).getName();
        // Убираем verify для mockIngredient2, так как он не используется в этом тесте
    }
}