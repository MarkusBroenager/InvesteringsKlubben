package Models.Model;

import Models.Interfaces.Portfolios;

import java.util.ArrayList;
import java.util.List;

public class PortfolioDKK implements Portfolios {


    private List<Holding> holdings;
    private double initialValue;
    private double liquidCash;

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
    public List<String> getPortfolioInformation() {
        List<String> portfolioInformation = new ArrayList<>();
        portfolioInformation.add("Started with: " + String.format("%.2f", initialValue) + " DKK" +
                "\nCurrent liquid cash: " + String.format("%.2f", liquidCash) + " DKK" +
                "\nCurrent portfolio value: " + String.format("%.2f", getPortfolioValueInDKK()) + " DKK" +
                "\nP&L in DKK: " + String.format("%.2f", getProfitOrLossInDKK()) + " P&L in percentage: " +
                String.format("%.2f", getProfitOrLossInPercentage()) + "%");
        for (Holding holding : holdings) {
            if (holding.getQuantity() != 0) {
                portfolioInformation.add(holding + " " +
                        String.format("%.2f", getPercentageOfPortfolio(holding.getValueOfHoldingInDKK())) +
                        "% of total portfolio value");
            }
        }
        return portfolioInformation;
    }

    public List<String> getSectorDistribution() {
        List<String> sectorList = new ArrayList<>();
        String sector = holdings.get(0).getSector();
        double sectorInvestment = 0;
        sectorList.add("Total cash: " + String.format("%.2f", getLiquidCash()) + " DKK Percentage of portfolio: " +
                String.format("%.2f", getPercentageOfPortfolio(getLiquidCash())) + "%");
        for (Holding holding : holdings) {
            if (sector.equalsIgnoreCase(holding.getSector())) {
                sectorInvestment += holding.getValueOfHoldingInDKK();
            } else if (!sector.equalsIgnoreCase(holding.getSector())) {
                if (sectorInvestment > 0) {
                    addToSectorList(sectorList, sector, sectorInvestment);
                }
                sector = holding.getSector();
                sectorInvestment = holding.getValueOfHoldingInDKK();
            }
        }
        if (!sectorList.contains(sector)) {
            addToSectorList(sectorList, sector, sectorInvestment);
        }
        return sectorList;
    }

    private void addToSectorList(List<String> sectorList, String sector, double sectorInvestment) {
        sectorList.add("Total invested in: " + sector + " is: " + String.format("%.2f", sectorInvestment) +
                " DKK Percentage of total investment: " +
                String.format("%.2f", getPercentageOfPortfolio(sectorInvestment)) + "%");
    }

    private double getPercentageOfPortfolio(double value) {
        return ((value - getPortfolioValueInDKK()) / (getPortfolioValueInDKK()) * 100) + 100;
    }

}
