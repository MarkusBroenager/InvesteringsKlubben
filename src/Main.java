import Controller.Controller;
import Repository.Interfaces.*;
import Repository.RepositoriesCSV.*;
import Services.Interfaces.PortfolioServices;
import Services.Interfaces.StockMarketServices;
import Services.Interfaces.TransactionServices;
import Services.Interfaces.UserServices;
import Services.ServicesCSV.*;
import UI.UserInterface;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryCSV("users.csv");
        StockMarketRepository stockMarketRepository = new StockMarketRepositoryCSV("stockMarket.csv");
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("currency.csv");
        TransactionRepository transactionRepository = new TransactionRepositoryCSV("transactions.csv");

        StockMarketServices stockMarketService = new StockMarketService(stockMarketRepository, currencyRepository);
        TransactionServices transactionService = new TransactionService(transactionRepository, currencyRepository);
        UserServices userService = new UserService(userRepository);
        PortfolioServices portfolioServices = new PortfolioService(currencyRepository, stockMarketRepository,
                transactionRepository, userRepository);

        Controller controller = new Controller(stockMarketService, transactionService, userService, portfolioServices);
        UserInterface ui = new UserInterface(controller);
        ui.start();
    }
}