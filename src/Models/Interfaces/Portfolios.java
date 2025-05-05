package Models.Interfaces;

import Models.Model.Holding;

import java.util.List;

public interface Portfolios {

    double getLiquidCash();

    List<Holding> getHoldings();

    double getPortfolioValueInDKK();

    double getProfitOrLossInDKK();

    double getProfitOrLossInPercentage();

    List<String> getPortfolioInformation();
}
