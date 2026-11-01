import java.util.List;

public class BankService {
    private final Repository<Account> accounts =
            new Repository<>(Account.class);

    public void openAccount(Account account) {
        Validator.requireValid(
                account != null, "Account cannot be null");

        Validator.requireValid(
                accounts.findById(account.getAccountNumber()) == null,
                "Account number already exists");

        accounts.save(account);
        TransactionLog.write(
                "OPEN | " + account.getAccountNumber());
    }

    public void deposit(String accountNumber, double rupees) {
        Account account = findAccount(accountNumber);
        long amount = Validator.rupeesToPaise(rupees);

        account.deposit(amount);
        TransactionLog.write(
                "DEPOSIT | " + accountNumber + " | Rs. " + rupees);
    }

    public void withdraw(String accountNumber, double rupees) {
        Account account = findAccount(accountNumber);
        long amount = Validator.rupeesToPaise(rupees);

        try {
            account.withdraw(amount);
        } catch (IllegalArgumentException e) {
            throw new BankException(e.getMessage());
        }

        TransactionLog.write(
                "WITHDRAW | " + accountNumber + " | Rs. " + rupees);
    }

    public void transfer(String from, String to, double rupees) {
        Account source = findAccount(from);
        Account destination = findAccount(to);
        long amount = Validator.rupeesToPaise(rupees);

        if (source == destination) {
            throw new BankException(
                    "Source and destination must be different");
        }

        try {
            source.withdraw(amount);
            try {
                destination.deposit(amount);
            } catch (RuntimeException e) {
                source.deposit(amount);
                throw e;
            }
        } catch (IllegalArgumentException e) {
            throw new BankException(e.getMessage());
        }

        TransactionLog.write(
                "TRANSFER | " + from + " -> " + to
                        + " | Rs. " + rupees);
    }

    public Account findAccount(String accountNumber) {
        Account account = accounts.findById(accountNumber);

        if (account == null) {
            throw new BankException("Account not found: " + accountNumber);
        }

        return account;
    }

    public List<Account> listAccounts() {
        return accounts.findAll();
    }
    public void saveState() {
        StatePersister.save(accounts.findAll());
    }

    public void loadState() {
        for (Account account : StatePersister.load()) {
            accounts.save(account);
        }
    }
}