package lt.viko.eif.ih.bakery.model;

import jakarta.xml.bind.annotation.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** This is a root class for the whole model, the bakery consists of its name, address, and customers.
 * This class has id, name, address variables and customerList so that bakery can possess customers in a list. For each of the variables there is getter and setter initialised.
 * Also, constructor is created for bakery without id so that this value can be assigned automatically for the database.
 * There is a method addCustomer that is used to add new customers to the customerList.
 * In this class toString() method is overridden for more comfortable viewing.
 */
@XmlType(
        propOrder = {"name", "address", "customerList"}
)
@XmlRootElement(
        name = "Bakery"
)
//@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "bakery")
public class Bakery
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private String address;
    @OneToMany(targetEntity = Customer.class, cascade = CascadeType.ALL)
    private List<Customer> customerList = new ArrayList<Customer>();

    public Bakery()
    {

    }
    public Bakery(String name, String address ) {
        this.name = name;
        this.address = address;

    }
    public String toString() {
        String Customers = new String();

        for(int i = 0; i < this.customerList.size(); ++i) {
            Customers = Customers +  ((Customer)this.customerList.get(i)).toString();
            if (i != this.customerList.size() - 1) {
                Customers = Customers + "\n";
            }
        }

        return String.format("Bakery:\n\tName = %s;\n\tAddress = %s;\n\tCustomers:\n%s", this.name, this.address, Customers);
    }
    public String getName() {
        return name;
    }
//    @XmlElement(
//            name = "bakery_name"
//    )

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
//    @XmlElement(
//            name = "bakery_address"
//    )


    public void setAddress(String address) {
        this.address = address;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    @XmlElement(
            name = "customer"
    )
    @XmlElementWrapper(
            name = "customers"
    )
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }
    public List<Customer> getCustomers() {
        return this.customerList;
    }
}
