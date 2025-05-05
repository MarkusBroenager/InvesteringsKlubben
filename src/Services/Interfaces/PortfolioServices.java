package Services.Interfaces;

import Models.Model.PortfolioDKK;

import java.util.List;

public interface PortfolioServices {

    PortfolioDKK getPortfolio(int userID);

    PortfolioDKK getCombinedUserPortfolio();

    List<String> getCombinedInvestmentPerSector(PortfolioDKK portfolio);

    List<String> getCombinedInvestmentPerStock();

    List<PortfolioDKK> getAllPortfolios();
}
