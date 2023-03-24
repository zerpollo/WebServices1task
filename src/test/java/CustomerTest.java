import lt.viko.eif.ih.bakery.model.Cart;
import lt.viko.eif.ih.bakery.model.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 The CustomerTest class is a junit test class that tests the functionality of the Customer class.
 It contains a testCustomerCart method that tests the addToCart method of the Customer class.
 */
     public class CustomerTest
     {
     /**
     Tests the addToCart method of the Customer class by adding three products to a customer's cart list
     and then checking if the customer's toString method contains the names of the three products.
     */
    @Test
    public void testCustomerCart() {
        Customer customer1 = new Customer("Ihor", "Halytskyi", "+380675321902");
        Cart cart1 = new Cart("Bread", 15);
        Cart cart2 = new Cart("Muffin", 25);
        Cart cart3 = new Cart("Pie", 35);
        customer1.addToCart(cart1);
        customer1.addToCart(cart2);
        customer1.addToCart(cart3);
        System.out.println(customer1);
        Assert.assertTrue(customer1.toString().contains("Bread"));
        Assert.assertTrue(customer1.toString().contains("Muffin"));
        Assert.assertTrue(customer1.toString().contains("Pie"));
    }
}

