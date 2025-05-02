import Models.Transaction;
import Services.ServicesCSV.*;

import java.util.List;

public class Controller {

    private StockMarketService stockMarketService;
    private TransactionService transactionService;
    private UserService userService;

    public Controller(StockMarketService stockMarketService, TransactionService transactionService,
                      UserService userService) {
        this.stockMarketService = stockMarketService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    public void start(){
        int userID = 1;
        List<Transaction> transactions = transactionService.getTransactionsForUser(userID);
        System.out.println("Showings transactions for " + userService.getUser(userID));
        for (Transaction t : transactions) {
            System.out.println(t);
            System.out.println(stockMarketService.getStock(t.getTicker()));
        }
    }

}
