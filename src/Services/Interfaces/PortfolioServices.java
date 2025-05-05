package Services.Interfaces;

import Models.Interfaces.Portfolios;

import java.util.List;

public interface PortfolioServices {

    Portfolios getPortfolio(int userID);

    Portfolios getCombinedUserPortfolio();

    List<Portfolios> getAllPortfolios();

}
