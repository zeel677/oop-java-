import java.util.concurrent.*;

public class TransactionProcessor {
    private final ConcurrentHashMap<String, Account> accounts =
            new ConcurrentHashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void depositAsync(String accountNumber, long amountPaise)
            throws InterruptedException, ExecutionException {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<?> result = executor.submit(
                    () -> account.deposit(amountPaise));
            result.get();
        } finally {
            executor.shutdown();
        }
    }
}