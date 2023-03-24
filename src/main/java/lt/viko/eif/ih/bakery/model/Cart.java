package lt.viko.eif.ih.bakery.model;

import jakarta.xml.bind.annotation.*;
import javax.persistence.*;

/** This is like a branch of Bakery class, the cart consists of id, name, and price.
 * This class has id, name, and price variables. For each of the variables there is getter and setter initialised.
 * Also, constructor is created for cart without id so that this value can be assigned automatically for the database.
 * In this class toString() method is overriden for more comfortable viewing.
 */
@XmlType(
        propOrder = {"id", "name", "price"}
)
@XmlRootElement(
        name = "Cart"
)
//@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "cart")
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private int price;
    public int getId() { return this.id; }
    @XmlElement(
            name = "id"
    )
    public void setId(int id) { this.id = id; }
    @XmlElement(
            name = "product_name"
    )
    public String getName() {
        return name;
    }

    @XmlElement(
            name = "product_price"
    )
    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public Cart()
    {

    }
    public Cart(String name, int price) {
    //    this.id = id;
        this.name = name;
        this.price = price;
    }
    public String toString() {
        return String.format("\tid = %d\n\tName of product = %s\n\tPrice = %s", this.id, this.name, this.price);
    }
}
