import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class usersReader {

    public static void readUsers() {

        try {
        File usersFile = new File("Resources/users.csv");
        Scanner reader = new Scanner(usersFile);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] column = line.split(";");
            System.out.println(column[0] + "       " +  column[1] + "     " + column[2] +
                    "               " + column[3] + "               " + column[4] + "          " +
                      column[5] + "         " + column[6]);
        }

        reader.close();

    } catch (FileNotFoundException e) {
            System.out.println("Reading of file failed. File could not be found.");
    }

}
}
