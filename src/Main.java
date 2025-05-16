
import Controller.Controller;
import Repository.Interfaces.*;
import Repository.RepositoriesCSV.*;
import Services.Interfaces.*;
import Services.ServicesCSV.*;

public class Main {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepositoryCSV("Resources/users.csv");
        StockMarketRepository stockMarketRepository = new StockMarketRepositoryCSV("Resources/stockMarket.csv");
        BondRepository bondRepository = new BondRepositoryCSV("Resources/bondMarket.csv");
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("Resources/currency.csv");
        TransactionRepository transactionRepository = new TransactionRepositoryCSV("Resources/transactions.csv");

        StockMarketServices stockMarketService = new StockMarketService(stockMarketRepository, bondRepository, currencyRepository);
        TransactionServices transactionService = new TransactionService(transactionRepository, currencyRepository);
        UserServices userService = new UserService(userRepository);
        PortfolioServices portfolioServices = new PortfolioService(currencyRepository, stockMarketRepository, bondRepository,
                transactionRepository, userRepository);

        Controller controller = new Controller(stockMarketService, transactionService, userService, portfolioServices);
        controller.start();
    }
}
