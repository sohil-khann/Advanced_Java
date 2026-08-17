import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void testAddItem() {
        ShoppingCart cart = new ShoppingCart();
        ShoppingCart.Item item = new ShoppingCart.Item("Book", 10.0);
        cart.addItem(item);
        assertEquals(1, cart.getItemCount());
        assertEquals(10.0, cart.getTotal(), 0.001);
    }

    @Test
    void testRemoveItem() {
        ShoppingCart cart = new ShoppingCart();
        ShoppingCart.Item item = new ShoppingCart.Item("Book", 10.0);
        cart.addItem(item);
        cart.removeItem(item);
        assertEquals(0, cart.getItemCount());
        assertEquals(0.0, cart.getTotal(), 0.001);
    }

    @Test
    void testRemoveNonExistentItem() {
        ShoppingCart cart = new ShoppingCart();
        ShoppingCart.Item item1 = new ShoppingCart.Item("Book", 10.0);
        ShoppingCart.Item item2 = new ShoppingCart.Item("Pen", 5.0);
        cart.addItem(item1);
        cart.removeItem(item2);
        assertEquals(1, cart.getItemCount());
    }

    @Test
    void testEmptyCartTotal() {
        ShoppingCart cart = new ShoppingCart();
        assertEquals(0.0, cart.getTotal(), 0.001);
    }

    @Test
    void testDiscountApplication() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCart.Item("Book", 100.0));
        cart.applyDiscount(0.2);
        assertEquals(80.0, cart.getTotal(), 0.001);
    }

    @Test
    void testInvalidDiscount() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> cart.applyDiscount(1.5));
    }

    @Test
    void testNegativePriceItem() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(new ShoppingCart.Item("Bad", -10.0)));
    }

    @Test
    void testMultipleItemsTotal() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new ShoppingCart.Item("Book", 10.0));
        cart.addItem(new ShoppingCart.Item("Pen", 5.0));
        cart.addItem(new ShoppingCart.Item("Notebook", 15.0));
        assertEquals(30.0, cart.getTotal(), 0.001);
    }
}
