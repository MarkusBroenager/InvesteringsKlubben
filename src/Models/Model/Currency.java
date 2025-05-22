package Models.Model;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class Currency {

    private String baseCurrency;
    private String quoteCurrency;
    private double rate;
    private LocalDate lastUpdated;

    public Currency(String baseCurrency, String quoteCurrency, double rate, LocalDate lastUpdated) {
        this.baseCurrency = baseCurrency;
        this.quoteCurrency = quoteCurrency;
        this.rate = rate;
        this.lastUpdated = lastUpdated;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public double getRate() {
        return rate;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##################");
        String checkRate = decimalFormat.format(rate);
        checkRate = checkRate.replace(',','.');
        if (checkRate.contains("0.0")) {
            int zeroCount = 0;
            for (char ch : checkRate.toCharArray()) {
                if (ch == '0') {
                    zeroCount++;
                } else if (ch=='.') {
                    continue;
                } else {
                    break;
                }
            }
            String decimals = String.valueOf(zeroCount + 1);
            System.out.println(decimals);
            return "1 " + baseCurrency + " is currently worth " +
                    String.format("%." + decimals + "f", Double.parseDouble(checkRate)) +
                    " " + quoteCurrency + ";" + lastUpdated;
        }
        return "1 " + baseCurrency + " is currently worth " + String.format("%.2f", rate) +
                " " + quoteCurrency + ";" + lastUpdated;
    }

}
