public class SavingsAccount extends Account {

    public SavingsAccount(String accountNumber,
                          Customer customer,
                          long openingBalancePaise) {
        super(accountNumber, customer, openingBalancePaise);
    }

    @Override
    protected long minimumBalancePaise() {
        return 10000;
    }
}