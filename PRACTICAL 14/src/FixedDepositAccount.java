public class FixedDepositAccount extends Account {
    private boolean locked = true;

    public FixedDepositAccount(String number, Customer customer,
                               long balance) {
        super(number, customer, balance);
    }

    public void unlock() {
        locked = false;
    }

    @Override
    protected long minimumBalancePaise() {
        return 0;
    }

    @Override
    public synchronized void withdraw(long amountPaise) {
        if (locked) {
            throw new IllegalArgumentException(
                    "Fixed deposit is locked");
        }

        super.withdraw(amountPaise);
    }
}