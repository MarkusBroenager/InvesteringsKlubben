package Services.Interfaces;

import Models.Model.Portfolio;

import java.util.List;

public interface PortfolioServices {

    Portfolio getPortfolio(int userID);

    Portfolio getCombinedUserPortfolio();

    List<String> getCombinedInvestmentPerSector();

    List<String> getCombinedInvestmentPerStock();

    List<Models.Model.Portfolio> getAllPortfolios();

}
