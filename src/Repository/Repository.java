package Repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository extends ReadOnlyRepository {

    private String file;

    public Repository(String file) {
        super(file);
        this.file = file;
    }

    public void writeLine(String line) {
        try (FileOutputStream out = new FileOutputStream(file, true)) {
            try (PrintStream writer = new PrintStream(out)) {
                writer.append(line+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
