package Repositories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Repository extends ReadOnlyRepository {
    private String file;

    public Repository(String file) {
        super(file);
        this.file = file;
    }

    public void appendLine(String line) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
