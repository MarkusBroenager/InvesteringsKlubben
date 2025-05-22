package Models.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortfolioDKK {


    private List<Holding> holdings;
    private double initialValue;
    private double liquidCash;
    private final static String blue = "\u001B[34m";
    private final static String standard = "\u001B[0m";
    private String fullName;

    public PortfolioDKK(List<Holding> holdings, double initialValue, double liquidCash, String fullName) {
        this.holdings = holdings;
        this.initialValue = initialValue;
        this.liquidCash = liquidCash;
        this.fullName = fullName;
    }

    public double getLiquidCash() {
        return liquidCash;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    public double getPortfolioValueInDKK() {
        double portfolioValue = liquidCash;
        for (Holding holding : holdings) {
            portfolioValue += holding.getValueOfHoldingInDKK();
        }
        return portfolioValue;
    }

    public double getProfitOrLossInDKK() {
        return getPortfolioValueInDKK() - initialValue;
    }

    public double getProfitOrLossInPercentage() {
        return (getPortfolioValueInDKK() - initialValue) / (initialValue) * 100;
    }


    public String toString() {
        return fullName + ": " + blue + "Started with: " + standard + String.format("%.2f", initialValue) + " DKK" +
                blue + "\nCurrent liquid cash: " + standard + String.format("%.2f", liquidCash) + " DKK" +
                blue + "\nCurrent portfolio value: " + standard + String.format("%.2f", getPortfolioValueInDKK()) + " DKK" +
                blue + "\nP&L in DKK: " + standard + String.format("%.2f", getProfitOrLossInDKK()) + blue + " P&L in percentage: " +
                standard + String.format("%.2f", getProfitOrLossInPercentage()) + "%";
    }

    public List<String> getPortfolioInformation() {
        List<String> portfolioInformation = new ArrayList<>();
        List<Holding> allHoldings = holdings;
        Collections.sort(allHoldings);
        Collections.reverse(allHoldings);
        for (Holding holding : allHoldings) {
            if (holding.getQuantity() != 0) {
                portfolioInformation.add(holding + " " +
                        String.format("%.2f", getPercentageOfPortfolio(holding.getValueOfHoldingInDKK())) +
                        "% of total portfolio value");
            }
        }
        return portfolioInformation;
    }

    public double getPercentageOfPortfolio(double value) {
        return ((value - getPortfolioValueInDKK()) / (getPortfolioValueInDKK()) * 100) + 100;
    }

    public Holding getHoldingFromTicker(String ticker) {
        for (Holding holding : holdings) {
            if (ticker.equalsIgnoreCase(holding.getTicker())) {
                return holding;
            }
        }
        return null;
    }

}
