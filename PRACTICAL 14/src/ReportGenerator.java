import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {
    public static void generate() {
        Path directory = Paths.get("data");
        Path report = directory.resolve("report.txt");

        try {
            Files.createDirectories(directory);

            try (BufferedWriter writer = Files.newBufferedWriter(report)) {
                writer.write("MINIBANK TRANSACTION REPORT");
                writer.newLine();

                try (DirectoryStream<Path> files =
                             Files.newDirectoryStream(directory, "*.log")) {
                    for (Path file : files) {
                        BasicFileAttributes attrs =
                                Files.readAttributes(
                                        file, BasicFileAttributes.class);

                        writer.write("File: " + file.getFileName());
                        writer.newLine();
                        writer.write("Size: " + attrs.size() + " bytes");
                        writer.newLine();

                        try (BufferedReader reader =
                                     Files.newBufferedReader(file)) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                writer.write(line);
                                writer.newLine();
                            }
                        }
                        writer.newLine();
                    }
                }
            }

            System.out.println("Report created: " + report.toAbsolutePath());
        } catch (IOException e) {
            throw new BankException("Unable to generate report");
        }
    }
}