import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.ih.bakery.model.Cart;
import org.junit.Test;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 The CartTest class tests if the cart object still has the data or it was lost during xml transformation of a cart object
 to and from XML using JAXB.
 */
public class CartTest {
    @Test
    public void testCartToXML() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Cart.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);

        Cart cart = new Cart("Bread", 25);

        marshaller.marshal(cart, System.out);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cart, sw);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Cart unmarshalledCart = (Cart) unmarshaller.unmarshal(new StringReader(sw.toString()));
        assertEquals("Bread", unmarshalledCart.getName());
    }
}
