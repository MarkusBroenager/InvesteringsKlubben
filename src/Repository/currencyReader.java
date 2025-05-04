package Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class currencyReader {

    public static void readCurrency() {

        try {
            File currencyFile = new File("Resources/currency.csv");
            Scanner reader = new Scanner(currencyFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] column = line.split(";");
                System.out.println(column[0] + "          " + column[1] + "          " + column[2] +
                        "               " + column[3]);
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Reading of file failed. File could not be found.");
        }

    }
}
