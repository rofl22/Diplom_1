package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для класса Burger")
class BurgerTest {

    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("setBuns должен устанавливать булочку")
    void setBunsShouldSetBun() {
        Bun bun = new Bun("black bun", 100f);
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    @DisplayName("addIngredient должен добавлять ингредиент")
    void addIngredientShouldAddIngredient() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    @DisplayName("removeIngredient должен удалять ингредиент")
    void removeIngredientShouldRemoveIngredient() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    @DisplayName("moveIngredient должен менять порядок ингредиентов")
    void moveIngredientShouldChangeOrder() {
        Ingredient first = new Ingredient(IngredientType.SAUCE, "sauce", 10f);
        Ingredient second = new Ingredient(IngredientType.FILLING, "cutlet", 20f);
        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(1, 0);

        assertEquals(second, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("getPrice должен считать цену корректно")
    void getPriceShouldCalculateCorrectly() {
        Bun bun = new Bun("black bun", 100f);
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(250f, burger.getPrice());
    }

    @Test
    @DisplayName("getReceipt должен содержать имя булочки")
    void getReceiptShouldContainBunName() {
        Bun bun = new Bun("black bun", 100f);
        burger.setBuns(bun);

        assertTrue(burger.getReceipt().contains("black bun"));
    }
}