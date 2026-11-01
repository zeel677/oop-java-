import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;

public class TransactionLog {
    private static final Path LOG_FILE =
            Paths.get("data", "transactions.log");

    public static synchronized void write(String message) {
        try {
            Files.createDirectories(LOG_FILE.getParent());

            String line = LocalDateTime.now() + " | "
                    + message + System.lineSeparator();

            Files.writeString(
                    LOG_FILE,
                    line,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new BankException("Unable to write transaction log");
        }
    }
}