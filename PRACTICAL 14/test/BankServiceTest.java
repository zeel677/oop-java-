import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BankServiceTest {
    @Test
    void depositIncreasesBalance() {
        Customer customer = new Customer(
                "C101", "Amit", "9876543210",
                "amit@example.com",
                new Address("Nadiad", "Gujarat", "387001"));
        SavingsAccount account =
                new SavingsAccount("AC101", customer, 100000);
        account.deposit(50000);
        assertEquals(150000, account.getBalancePaise());
    }
    @Test
    void withdrawalFailsWhenBalanceIsInsufficient() {
        Customer customer = new Customer(
                "C102", "Neha", "9876543211",
                "neha@example.com",
                new Address("Nadiad", "Gujarat", "387001"));
        SavingsAccount account =
                new SavingsAccount("AC102", customer, 20000);
        assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(15000));
    }
    @Test
    void repositoryCanFindSavedAccount() {
        Customer customer = new Customer(
                "C103", "Raj", "9876543212",
                "raj@example.com",
                new Address("Nadiad", "Gujarat", "387001"));
        Account account =
                new SavingsAccount("AC103", customer, 50000);
        Repository<Account> repository =
                new Repository<>(Account.class);
        repository.save(account);
        assertNotNull(repository.findById("AC103"));
        assertEquals(1, repository.count());
    }
}