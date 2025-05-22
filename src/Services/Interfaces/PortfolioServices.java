package Services.Interfaces;

import Models.Model.PortfolioDKK;

import java.util.Comparator;
import java.util.List;

public interface PortfolioServices {

    PortfolioDKK getPortfolio(int userID);

    PortfolioDKK getCombinedUserPortfolio();

    List<String> getCombinedInvestmentPerSector();

    List<PortfolioDKK> getAllPortfolios();

    List<PortfolioDKK> viewProfitAndLossSortedPortfolios(Comparator<PortfolioDKK> comparator);
}