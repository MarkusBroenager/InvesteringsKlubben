import Repository.Interfaces.*;
import Repository.RepositoriesCSV.*;
import Services.*;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryCSV("users.csv");
        StockMarketRepository stockMarketRepository = new StockMarketRepositoryCSV("stockMarket.csv");
        CurrencyRepository currencyRepository = new CurrencyRepositoryCSV("currency.csv");
        TransactionRepository transactionRepository = new TransactionRepositoryCSV("transactions.csv");

        StockMarketService stockMarketService = new StockMarketService(stockMarketRepository, currencyRepository);
        TransactionService transactionService = new TransactionService(transactionRepository);
        UserService userService = new UserService(userRepository);

        Controller controller = new Controller(stockMarketService, transactionService, userService);
        controller.start();
    }
}