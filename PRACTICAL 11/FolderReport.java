import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
public class FolderReport {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("logs");
        Path output = Paths.get("folder-report.txt");

        Files.createDirectories(folder);

        try (BufferedWriter writer = Files.newBufferedWriter(output)) {
            Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(
                        Path file, BasicFileAttributes attrs)
                        throws IOException {

                    writer.write("Name: " + file.getFileName()
                            + " | Size: " + attrs.size()
                            + " bytes | Modified: "
                            + attrs.lastModifiedTime());
                    writer.newLine();

                    return FileVisitResult.CONTINUE;
                }
            });
        }

        System.out.println("Folder report created.");
    }
}
