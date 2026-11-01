import java.io.*;
import java.nio.file.*;
import java.util.List;

public class StatePersister {
    private static final Path FILE =
            Paths.get("data", "accounts.dat");

    public static void save(List<Account> accounts) {
        try {
            Files.createDirectories(FILE.getParent());

            try (ObjectOutputStream out = new ObjectOutputStream(
                    Files.newOutputStream(FILE))) {
                out.writeObject(accounts.toArray(new Account[0]));
            }
        } catch (IOException e) {
            throw new BankException("Unable to save account data");
        }
    }

    public static Account[] load() {
        if (!Files.exists(FILE)) {
            return new Account[0];
        }

        try (ObjectInputStream in = new ObjectInputStream(
                Files.newInputStream(FILE))) {
            return (Account[]) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new BankException("Unable to load account data");
        }
    }
}