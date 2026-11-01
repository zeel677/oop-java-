public class CurrentAccount extends Account {
    private long overdraftPaise;

    public CurrentAccount(String number, Customer customer,
                          long balance, long overdraftPaise) {
        super(number, customer, balance);
        this.overdraftPaise = overdraftPaise;
    }

    @Override
    protected long minimumBalancePaise() {
        return -overdraftPaise;
    }
}