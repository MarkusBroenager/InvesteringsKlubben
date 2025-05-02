import Models.Model.Transaction;
import Services.Interfaces.PortfolioServices;
import Services.Interfaces.StockMarketServices;
import Services.Interfaces.TransactionServices;
import Services.Interfaces.UserServices;
import Services.ServicesCSV.*;

import java.util.List;

public class Controller {

    private StockMarketServices stockMarketService;
    private TransactionServices transactionService;
    private UserServices userService;
    private PortfolioServices portfolioService;

    public Controller(StockMarketServices stockMarketService, TransactionServices transactionService,
                      UserServices userService, PortfolioServices portfolioService) {
        this.stockMarketService = stockMarketService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.portfolioService = portfolioService;
    }

    public void start() {
        List<String> portfolio = portfolioService.getPortfolio(5).getPortfolioInformation();
        List<String> portfolio2 = portfolioService.getCombinedUserPortfolio().getPortfolioInformation();

        for(String s : portfolio){
            System.out.println(s);
        }

        System.out.println("");

        for(String s : portfolio2){
            System.out.println(s);
        }
    }
}
