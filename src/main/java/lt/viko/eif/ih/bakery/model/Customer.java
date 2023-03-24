package lt.viko.eif.ih.bakery.model;

import jakarta.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** This is like a branch of Bakery class, the customer consists of id, first name, last name, phone number, and products that customer has in cart.
 * This class has id, name, address variables and cartList so that customer can possess products in a list. For each of the variables there is getter and setter initialised.
 * Also, constructor is created for customer without id so that this value can be assigned automatically for the database.
 * There is a method addToCart that is used to add new product to the cartList.
 * In this class toString() method is overriden for more comfortable viewing.
 */
@XmlType(
        propOrder = {"id", "firstName", "lastName", "phoneNumber", "cartList"}
)
@XmlRootElement(
        name = "Customer"
)
//@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "customer")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToMany(targetEntity = Cart.class, cascade = CascadeType.ALL)
    private List<Cart> cartList = new ArrayList<>();

    public Customer()
    {

    }
    public Customer(String firstName, String lastName, String phoneNumber) {
  //      this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public List<Cart> getCartList() {
        return this.cartList;
    }
    @XmlElement(
            name = "Cart"
    )
    @XmlElementWrapper(
            name = "Products"
    )
    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public String toString() {
        String CartProducts = new String();

        for(int i = 0; i < this.cartList.size(); ++i) {
            CartProducts = CartProducts + "\t\tProduct:\n" + ((Cart)this.cartList.get(i)).toString();
            if (i != this.cartList.size() - 1) {
                CartProducts = CartProducts + "\n";
            }
        }

        return String.format("\tFirst Name = %s;\n\tLast Name = %s;\n\tProducts in cart:\n%s", this.firstName, this.lastName, CartProducts);
    }
    public int getId() { return id; }
    @XmlElement(
            name = "customer_id"
    )
    public void setId(int id){ this.id = id; }

    public String getFirstName() { return firstName; }
    @XmlElement(
            name = "first_name"
    )
    public void setFirstName(String firstName){ this.firstName = firstName; }

    public String getLastName() { return lastName; }
    @XmlElement(
            name = "last_name"
    )
    public void setLastName(String lastName){ this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    @XmlElement(
            name = "phone_number"
    )
    public void setPhoneNumber(String phoneNumber){ this.phoneNumber = phoneNumber; }
    public void addToCart(Cart cart) {cartList.add(cart);}

}
