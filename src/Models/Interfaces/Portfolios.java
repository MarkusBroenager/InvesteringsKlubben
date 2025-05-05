package Models.Interfaces;

import java.util.List;

public interface Portfolios {

    double getLiquidCash();

    double getPortfolioValueInDKK();

    double getProfitOrLossInDKK();

    double getProfitOrLossInPercentage();

    List<String> getPortfolioInformation();

    double getPercentageOfSector(String sector);
}
