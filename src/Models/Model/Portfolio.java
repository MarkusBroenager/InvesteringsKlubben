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
        return (liquidCash - initialValue) / (initialValue) * 100;
    }

    public List<String> getPortfolioInformation() {
        List<String> portfolio = new ArrayList<>();
        portfolio.add("Started with: " + initialValue + holdings.get(0).getCurrency().getBaseCurrency() +
                "\nCurrent liquid cash: " + liquidCash + " " + holdings.get(0).getCurrency().getBaseCurrency() +
                "\nCurrent portfolio value: " + getPortfolioValueInDKK() + holdings.get(0).getCurrency().getBaseCurrency());
        for (Holding holding : holdings) {
            portfolio.add(holding.toString());
        }
        return portfolio;
    }

}
