import java.io.Serializable;
import java.util.Objects;

public abstract class Account
        implements Serializable, Comparable<Account> {

    @Id
    protected String accountNumber;

    protected Customer customer;
    protected long balancePaise;
    protected transient String sessionNote;

    public Account(String accountNumber, Customer customer,
                   long openingBalancePaise) {
        if (openingBalancePaise < 0) {
            throw new IllegalArgumentException("Negative opening balance");
        }

        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balancePaise = openingBalancePaise;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public synchronized long getBalancePaise() {
        return balancePaise;
    }

    public synchronized void deposit(long amountPaise) {
        if (amountPaise <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        balancePaise = Math.addExact(balancePaise, amountPaise);
    }

    public synchronized void withdraw(long amountPaise) {
        if (amountPaise <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (balancePaise - amountPaise < minimumBalancePaise()) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        balancePaise -= amountPaise;
    }

    protected abstract long minimumBalancePaise();

    @Override
    public int compareTo(Account other) {
        return accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account)) return false;

        Account other = (Account) obj;
        return Objects.equals(accountNumber, other.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return accountNumber + " | " + customer.getName()
                + " | Balance: Rs. " + String.format("%.2f",
                balancePaise / 100.0);
    }
}