package Models.Model;

import java.util.ArrayList;
import java.util.List;

public class PortfolioDKK {


    private List<Holding> holdings;
    private double initialValue;
    private double liquidCash;

    public PortfolioDKK(List<Holding> holdings, double initialValue, double liquidCash) {
        this.holdings = holdings;
        this.initialValue = initialValue;
        this.liquidCash = liquidCash;
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
        return "Started with: " + String.format("%.2f", initialValue) + " DKK" +
                "\nCurrent liquid cash: " + String.format("%.2f", liquidCash) + " DKK" +
                "\nCurrent portfolio value: " + String.format("%.2f", getPortfolioValueInDKK()) + " DKK" +
                "\nP&L in DKK: " + String.format("%.2f", getProfitOrLossInDKK()) + " P&L in percentage: " +
                String.format("%.2f", getProfitOrLossInPercentage()) + "%";
    }

    public List<String> getPortfolioInformation() {
        List<String> portfolioInformation = new ArrayList<>();
        for (Holding holding : holdings) {
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
