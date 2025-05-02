package PersistenceLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class stockMarketReader {

    public static void readStockMarket() {

        try {
            File stockMarketFile = new File("Resources/stockMarket.csv");
            Scanner reader = new Scanner(stockMarketFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] column = line.split(";");
                System.out.println(column[0] + "        " + column[1] + "                    " + column[2] +
                        "                         " + column[3] + "                         " + column[4] +
                        "                    " + column[5] + "                      " + column[6] +
                        "                         " + column[7] + "               " + column[8]);
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Reading of file failed. File could not be found.");
        }

    }
}
