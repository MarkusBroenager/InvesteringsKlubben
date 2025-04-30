import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("src//users.csv");
        Path path = Paths.get("src//users.csv");
        ArrayList<String> users = new ArrayList<>();


        try {
            BufferedReader reader = Files.newBufferedReader(path);
            String string = null;

            while ((string = reader.readLine()) != null) {
                string = string.split(";")[0];
                System.out.println(string);

                String userID = string.split(";")[0];
                //String fullName = string.split(";")[1];

                users.add(userID);
                //users.add(fullName);
                System.out.println(users);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }






    }
}