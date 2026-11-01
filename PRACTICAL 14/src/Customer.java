import java.io.Serializable;

public class Customer implements Serializable {
    @Id
    private String customerId;
    private String name;
    private String mobile;
    private String email;
    private Address address;

    public Customer(String customerId, String name,
                    String mobile, String email, Address address) {
        this.customerId = customerId;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return customerId + " | " + name + " | " + mobile
                + " | " + email + " | " + address;
    }
}