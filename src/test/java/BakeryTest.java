import lt.viko.eif.ih.bakery.model.Bakery;
import lt.viko.eif.ih.bakery.model.Cart;
import lt.viko.eif.ih.bakery.model.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 This class tests the toString method of the Bakery class
 */
public class BakeryTest {

    @Test
    public void testToString() {
        Customer customer1 = new Customer( "Ihor", "Halytskyi", "+380675321902");
        Bakery bakery1 = new Bakery("Baking Bread", "Studentu 47");
        Cart cart = new Cart( "Bread", 25);
        bakery1.addCustomer(customer1);
        customer1.addToCart(cart);

        Assert.assertTrue(bakery1.toString().contains("Ihor"));
        Assert.assertTrue(bakery1.toString().contains("Halytskyi"));
    }
}






