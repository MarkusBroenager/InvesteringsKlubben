package Models.Model;

import Models.Interfaces.Portfolios;

import java.util.ArrayList;
import java.util.List;

public class Portfolio implements Portfolios {


    private List<Holding> holdings;
    private double initialValue;
    private double liquidCash;

    public Portfolio(List<Holding> holdings, double initialValue, double liquidCash) {
        this.holdings = holdings;
        this.initialValue = initialValue;
        this.liquidCash = liquidCash;
    }

    @Override
    public double getLiquidCash() {
        return liquidCash;
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
    public double getPercentageOfSector(String sector) {
        double sectorInvestment = 0;
        for (Holding holding : holdings) {
            if (holding.getSector().equalsIgnoreCase(sector)) {
                sectorInvestment += holding.getValueOfHoldingInDKK();
            }
        }
        return getPercentageOfPortfolio(sectorInvestment);
    }

    @Override
    public List<String> getPortfolioInformation() {
        List<String> portfolio = new ArrayList<>();
        portfolio.add("Started with: " + String.format("%.2f", initialValue) + " DKK" +
                "\nCurrent liquid cash: " + String.format("%.2f", liquidCash) + " DKK" +
                "\nCurrent portfolio value: " + String.format("%.2f", getPortfolioValueInDKK()) + " DKK" +
                "\nP&L in DKK: " + String.format("%.2f", getProfitOrLossInDKK()) + " P&L in percentage: " +
                String.format("%.2f", getProfitOrLossInPercentage()) + "%");
        for (Holding holding : holdings) {
            if (holding.getQuantity() != 0) {
                portfolio.add(holding + " " +
                        String.format("%.2f", getPercentageOfPortfolio(holding.getValueOfHoldingInDKK())) +
                        "% of total portfolio value");
            }
        }
        return portfolio;
    }

    private double getPercentageOfPortfolio(double value) {
        return ((value - getPortfolioValueInDKK()) / (getPortfolioValueInDKK()) * 100) + 100;
    }

}
