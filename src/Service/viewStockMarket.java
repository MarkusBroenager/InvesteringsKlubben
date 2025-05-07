package Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class viewStockMarket {

    private String ticker;
    private String companyName;
    private String sector;
    private double price;
    private String currency;
    private String rating;
    private double dividend_yield;
    private String market;
    private String lastUpdated;
    private ArrayList<viewStockMarket> arraylist;

    public viewStockMarket(String ticker, String companyName, String sector, double price, String currency,
                           String rating, double dividend_yield, String market, String lastUpdated) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.price = price;
        this.currency = currency;
        this.rating = rating;
        this.dividend_yield = dividend_yield;
        this.market = market;
        this.lastUpdated = lastUpdated;
    }

    public viewStockMarket() {
        this.arraylist = new ArrayList<>();
    }

    public static void loadStockMarketData(ArrayList<viewStockMarket> list) {

        File stockMarketFile = new File("Resources/stockMarket.csv");
        try {
            Scanner scanner = new Scanner(stockMarketFile);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if (line.contains("ticker")) {
                    System.out.println();
                } else {
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter(";");

                    String ticker = lineScanner.next();
                    String companyName = lineScanner.next();
                    String sector = lineScanner.next();
                    double price = Double.parseDouble(lineScanner.next());
                    String currency = lineScanner.next();
                    String rating = lineScanner.next();
                    double dividend_yield = Double.parseDouble(lineScanner.next());
                    String market = lineScanner.next();
                    String lastUpdated = lineScanner.next();

                    list.add(new viewStockMarket(ticker, companyName, sector, price, currency, rating, dividend_yield,
                            market, lastUpdated));
                }
            }
        } catch(FileNotFoundException e){
                throw new RuntimeException(e);
            }

    }

    public static void showStockMarket(ArrayList<viewStockMarket> list) {

        int i;

        for (i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());

        }
    }
    public String toString () {
        return ticker + "  " + companyName + "  " + sector + "  " + price + "  " + currency + "  " +
                rating + "  " + dividend_yield + "  " + market + "  " + lastUpdated;
    }


    }




