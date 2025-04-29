package Repository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadOnlyRepository {

    private Path path;

    public ReadOnlyRepository(String file){
        this.path = Paths.get(file);
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
