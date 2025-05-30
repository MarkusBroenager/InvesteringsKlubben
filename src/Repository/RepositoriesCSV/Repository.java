package Repository.RepositoriesCSV;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Repository extends ReadOnlyRepository {

    private final String file;

    public Repository(String file) {
        super(file);
        this.file = file;
    }

    public void appendLine(String line) {
        try (FileOutputStream out = new FileOutputStream(file, true)) {
            try (PrintStream writer = new PrintStream(out)) {
                writer.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
