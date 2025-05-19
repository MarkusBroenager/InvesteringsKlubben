package Models.Model;

import Models.Interfaces.Portfolios;

import java.util.ArrayList;
import java.util.List;

public class PortfolioDKK implements Portfolios {


    private List<Holding> holdings;
    private double initialValue;
    private double liquidCash;
    private final static String blue = "\u001B[34m";
    private final static String standard = "\u001B[0m";

    public PortfolioDKK(List<Holding> holdings, double initialValue, double liquidCash) {
        this.holdings = holdings;
        this.initialValue = initialValue;
        this.liquidCash = liquidCash;
    }

    @Override
    public double getLiquidCash() {
        return liquidCash;
    }

    @Override
    public List<Holding> getHoldings() {
        return holdings;
    }

    @Override
    public double getPortfolioValueInDKK() {
        double portfolioValue = liquidCash;
        for (Holding holding : holdings) {
            portfolioValue += holding.getValueOfHoldingInDKK();
        }
        return portfolioValue;
    }

    @Override
    public double getProfitOrLossInDKK() {
        return getPortfolioValueInDKK() - initialValue;
    }

    @Override
    public double getProfitOrLossInPercentage() {
        return (getPortfolioValueInDKK() - initialValue) / (initialValue) * 100;
    }

    @Override
    public String toString(){
        return blue + "Started with: " + standard + String.format("%.2f", initialValue) + " DKK" +
                blue + "\nCurrent liquid cash: " + standard + String.format("%.2f", liquidCash) + " DKK" +
                blue + "\nCurrent portfolio value: " + standard + String.format("%.2f", getPortfolioValueInDKK()) + " DKK" +
                blue + "\nP&L in DKK: " + standard + String.format("%.2f", getProfitOrLossInDKK()) + blue + " P&L in percentage: " +
                standard + String.format("%.2f", getProfitOrLossInPercentage()) + "%";
    }



    @Override
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

    public Holding getHoldingFromTicker(String ticker){
        for(Holding holding : holdings){
            if(ticker.equalsIgnoreCase(holding.getTicker())){
                return holding;
            }
        }
        return null;
    }

}
