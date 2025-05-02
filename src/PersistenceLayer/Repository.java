package PersistenceLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repository {

    public List<String> readFile(File file){
        List<String> fileContents = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            reader.nextLine();
            while (reader.hasNextLine()) {
                fileContents.add(reader.nextLine());
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Reading of file failed. File could not be found.");
        }
        return fileContents;
    }

}
