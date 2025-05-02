package Models.Interfaces;

import Models.Model.Holding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Portfolios {

    public double getLiquidCash();

    public double getPortfolioValueInDKK();

    public double getProfitOrLossInDKK();

    public double getProfitOrLossInPercentage();

    public List<String> getPortfolioInformation();
}
