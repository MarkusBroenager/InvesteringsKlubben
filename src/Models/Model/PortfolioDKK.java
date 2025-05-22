package Models.Model;

import Services.ServicesCSV.ColorService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PortfolioDKK {


    private List<Holding> holdings;
    private double initialValue;
    private double liquidCash;
    private final String BLUE;
    private final String STANDARD;
    private String fullName;

    public PortfolioDKK(List<Holding> holdings, double initialValue, double liquidCash, String fullName) {
        this.holdings = holdings;
        this.initialValue = initialValue;
        this.liquidCash = liquidCash;
        this.fullName = fullName;
        //setting color
        this.BLUE = ColorService.getBlueColor();
        this.STANDARD = ColorService.getStandardColor();
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
        return fullName + ": " + BLUE + "Initial investment: " + STANDARD + String.format("%.2f", initialValue) + " DKK" +
                BLUE + "\nCurrent liquid cash: " + STANDARD + String.format("%.2f", liquidCash) + " DKK" +
                BLUE + "\nCurrent portfolio value: " + STANDARD + String.format("%.2f", getPortfolioValueInDKK()) + " DKK" +
                BLUE + "\nP&L in DKK: " + STANDARD + String.format("%.2f", getProfitOrLossInDKK()) + BLUE + " P&L in percentage: " +
                STANDARD + String.format("%.2f", getProfitOrLossInPercentage()) + "%";
    }

    public List<String> getPortfolioInformation() {
        List<String> portfolioInformation = new ArrayList<>();
        List<Holding> allHoldings = holdings;
        Collections.sort(allHoldings);
        Collections.reverse(allHoldings);
        for (Holding holding : allHoldings) {
            if (holding.getQuantity() != 0) {
                portfolioInformation.add(holding.tableToString() + ";" +
                        String.format("%.2f", getPercentageOfPortfolio(holding.getValueOfHoldingInDKK())) +
                        "%");
            }
        }
        return portfolioInformation;
    }

    public double getPercentageOfPortfolio(double value) {
        return ((value - (getPortfolioValueInDKK()-liquidCash)) / ((getPortfolioValueInDKK()-liquidCash)) * 100) + 100;
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
