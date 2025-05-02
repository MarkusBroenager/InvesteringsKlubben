package Services.Interfaces;

import Models.Interfaces.Portfolios;
import Models.Model.Holding;

import java.util.List;

public interface PortfolioServices {

    public Portfolios getPortfolio(int userID);

    public Portfolios getCombinedUserPortfolio();

}
