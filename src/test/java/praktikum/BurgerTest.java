package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для Burger")
class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("setBuns должен устанавливать булочку")
    void setBunsShouldSetBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    @DisplayName("addIngredient должен добавлять ингредиент")
    void addIngredientShouldAddIngredient() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    @DisplayName("removeIngredient должен удалять ингредиент")
    void removeIngredientShouldRemoveIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    @DisplayName("moveIngredient должен менять порядок")
    void moveIngredientShouldChangeOrder() {
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        burger.addIngredient(second);

        burger.moveIngredient(1, 0);

        assertEquals(second, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("getPrice должен вызывать getPrice у булочки")
    void getPriceShouldCallBunGetPrice() {
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        burger.getPrice();

        verify(bun, times(1)).getPrice();
    }

    @Test
    @DisplayName("getPrice должен учитывать цену ингредиента")
    void getPriceShouldIncludeIngredientPrice() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(250f, burger.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {"black bun", "white bun"})
    @DisplayName("getReceipt должен содержать имя булочки")
    void getReceiptShouldContainBunName(String name) {
        when(bun.getName()).thenReturn(name);
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);

        String receipt = burger.getReceipt();

        assertEquals(true, receipt.contains(name));
    }
}