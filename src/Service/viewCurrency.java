package Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class viewCurrency {

    private String baseCurrency;
    private String qouteCurrency;
    private double rate;
    private String lastUpdated;
    private ArrayList<viewCurrency> list;

    public viewCurrency(String baseCurrency, String qouteCurrency, double rate, String lastUpdated) {
        this.baseCurrency = baseCurrency;
        this.qouteCurrency = qouteCurrency;
        this.rate = rate;
        this.lastUpdated = lastUpdated;
    }

    public viewCurrency() {
        this.list = new ArrayList<>();
    }

    public static void loadCurrency(ArrayList<viewCurrency> list) {

        File currencyFile = new File("Resources/currency.csv");
        try {
            Scanner scanner = new Scanner(currencyFile);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.contains("rate")) {
                    System.out.println();
                } else {
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter(";");

                    String baseCurrency = lineScanner.next();
                    String qouteCurrency = lineScanner.next();
                    double rate = Double.parseDouble(lineScanner.next());
                    String lastUpdated = lineScanner.next();

                    list.add(new viewCurrency(baseCurrency, qouteCurrency, rate, lastUpdated));

                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void showCurrency(ArrayList<viewCurrency> list) {

        int i;

        for (i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }

    public String toString() {
        return this.baseCurrency + "  " + this.qouteCurrency + "  " + this.rate + "  " + this.lastUpdated;
    }
}
