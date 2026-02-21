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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для класса Burger")
class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBlackBun;

    @Mock
    private Ingredient mockSauceIngredient;

    @Mock
    private Ingredient mockFillingIngredient;

    @Mock
    private Ingredient mockExtraIngredient;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("setBuns() должен устанавливать булочку")
    void setBunsShouldSetBun() {
        burger.setBuns(mockBlackBun);

        assertEquals(mockBlackBun, burger.bun, "Булочка должна быть установлена");
    }

    @Test
    @DisplayName("addIngredient() должен добавлять ингредиент в список")
    void addIngredientShouldAddIngredient() {
        burger.addIngredient(mockSauceIngredient);

        assertEquals(1, burger.ingredients.size(), "Должен быть один ингредиент");
        assertEquals(mockSauceIngredient, burger.ingredients.get(0), "Добавленный ингредиент должен быть в списке");
    }

    @Test
    @DisplayName("removeIngredient() должен удалять ингредиент по индексу")
    void removeIngredientShouldRemoveIngredientAtIndex() {
        burger.addIngredient(mockSauceIngredient);
        burger.addIngredient(mockFillingIngredient);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size(), "Должен остаться один ингредиент");
        assertEquals(mockFillingIngredient, burger.ingredients.get(0), "Должен остаться второй ингредиент");
    }

    @Test
    @DisplayName("moveIngredient() должен перемещать ингредиент на новую позицию")
    void moveIngredientShouldMoveIngredientToNewIndex() {
        burger.addIngredient(mockSauceIngredient);
        burger.addIngredient(mockFillingIngredient);
        burger.addIngredient(mockExtraIngredient);

        burger.moveIngredient(2, 0);

        assertEquals(mockExtraIngredient, burger.ingredients.get(0), "Ингредиент с индексом 2 должен быть на позиции 0");
        assertEquals(mockSauceIngredient, burger.ingredients.get(1), "Ингредиент с индексом 0 должен быть на позиции 1");
        assertEquals(mockFillingIngredient, burger.ingredients.get(2), "Ингредиент с индексом 1 должен быть на позиции 2");
    }

    @Test
    @DisplayName("getPrice() должен корректно рассчитывать цену с моками")
    void getPriceShouldCalculateCorrectPriceWithMocks() {
        when(mockBlackBun.getPrice()).thenReturn(100f);
        when(mockSauceIngredient.getPrice()).thenReturn(50f);
        when(mockFillingIngredient.getPrice()).thenReturn(75f);

        burger.setBuns(mockBlackBun);
        burger.addIngredient(mockSauceIngredient);
        burger.addIngredient(mockFillingIngredient);

        float expectedPrice = 100f * 2 + 50f + 75f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001, "Цена должна быть рассчитана корректно");

        verify(mockBlackBun, times(1)).getPrice();
        verify(mockSauceIngredient, times(1)).getPrice();
        verify(mockFillingIngredient, times(1)).getPrice();
    }

    @Test
    @DisplayName("getPrice() с пустым списком ингредиентов")
    void getPriceWithEmptyIngredients() {
        when(mockBlackBun.getPrice()).thenReturn(150f);

        burger.setBuns(mockBlackBun);

        float expectedPrice = 150f * 2;
        assertEquals(expectedPrice, burger.getPrice(), 0.001, "Цена должна быть равна удвоенной цене булочки");
    }

    @ParameterizedTest
    @ValueSource(strings = {"black bun", "white bun", "red bun"})
    @DisplayName("getReceipt() должен формировать корректный чек")
    void getReceiptShouldReturnCorrectReceipt(String bunName) {
        Bun realBlackBun = new Bun(bunName, 100f);
        Ingredient realSauceIngredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);
        Ingredient realFillingIngredient = new Ingredient(IngredientType.FILLING, "cutlet", 75f);

        burger.setBuns(realBlackBun);
        burger.addIngredient(realSauceIngredient);
        burger.addIngredient(realFillingIngredient);

        float expectedPrice = 100f * 2 + 50f + 75f;
        String expectedReceipt = String.format("(==== %s ====)%n", bunName) +
                String.format("= %s %s =%n", "sauce", "hot sauce") +
                String.format("= %s %s =%n", "filling", "cutlet") +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", expectedPrice);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt, "Чек должен полностью совпадать с ожидаемым");
    }

    @Test
    @DisplayName("getReceipt() с пустым бургером")
    void getReceiptWithEmptyBurger() {
        String bunName = "test bun";
        Bun realBlackBun = new Bun(bunName, 100f);
        burger.setBuns(realBlackBun);

        float expectedPrice = 200f;
        String expectedReceipt = String.format("(==== %s ====)%n", bunName) +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", expectedPrice);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt, "Чек для пустого бургера должен полностью совпадать с ожидаемым");
    }

    @Test
    @DisplayName("Тест с использованием реальных объектов из Database")
    void testWithRealDatabaseObjects() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();

        Bun expectedBlackBun = buns.get(0);
        Ingredient expectedSourCream = ingredients.get(1);
        Ingredient expectedDinosaur = ingredients.get(4);

        burger.setBuns(expectedBlackBun);
        burger.addIngredient(expectedSourCream);
        burger.addIngredient(expectedDinosaur);

        assertEquals(expectedBlackBun, burger.bun);
        assertEquals(2, burger.ingredients.size());
        assertEquals(expectedSourCream, burger.ingredients.get(0));
        assertEquals(expectedDinosaur, burger.ingredients.get(1));

        float expectedPrice = 100f * 2 + 200f + 200f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Тест всех методов Burger с моками")
    void testAllBurgerMethodsWithMocks() {
        when(mockBlackBun.getName()).thenReturn("mock black bun");
        when(mockBlackBun.getPrice()).thenReturn(50f);
        when(mockSauceIngredient.getName()).thenReturn("mock chili sauce");
        when(mockSauceIngredient.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(mockBlackBun);
        burger.addIngredient(mockSauceIngredient);

        assertEquals(mockBlackBun, burger.bun);
        assertEquals(1, burger.ingredients.size());

        float expectedPrice = 50f * 2;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);

        String expectedReceipt = String.format("(==== %s ====)%n", "mock black bun") +
                String.format("= %s %s =%n", "sauce", "mock chili sauce") +
                String.format("(==== %s ====)%n", "mock black bun") +
                String.format("%nPrice: %f%n", expectedPrice);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt, "Чек должен полностью совпадать с ожидаемым");

        verify(mockBlackBun, atLeastOnce()).getName();
        verify(mockSauceIngredient, atLeastOnce()).getType();
        verify(mockSauceIngredient, atLeastOnce()).getName();
    }
}