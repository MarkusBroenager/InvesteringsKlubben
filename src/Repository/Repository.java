package Repository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {

    private String file;
    private Path path;

    public Repository(String file) {
        this.file = file;
        this.path = Paths.get(file);
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

    public List<String> readFile() {
        List<String> getFileAsListOfStrings = new ArrayList<>();
        try(Scanner reader = new Scanner(path)){
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                getFileAsListOfStrings.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getFileAsListOfStrings;
    }

}
