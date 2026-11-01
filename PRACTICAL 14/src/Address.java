import java.io.Serializable;

public class Address implements Serializable, Cloneable {
    private String city;
    private String state;
    private String pincode;

    public Address(String city, String state, String pincode) {
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public Address clone() {
        return new Address(city, state, pincode);
    }

    @Override
    public String toString() {
        return city + ", " + state + " - " + pincode;
    }
}