package Repository.RepositoriesCSV;

import Models.Interfaces.Transactions;
import Models.Model.Transaction;
import Repository.Interfaces.TransactionRepository;
import Services.ServicesCSV.DataServices;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryCSV extends Repository implements TransactionRepository {
    public TransactionRepositoryCSV(String file) {
        super(file);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        List<String> list = super.readFile();
        list.removeFirst();
        for (String line : list) {
            String[] lineSplit = line.split(";");
            transactions.add(new Transaction(Integer.parseInt(lineSplit[0]),
                    Integer.parseInt(lineSplit[1]),
                    DataServices.getLocalDate(lineSplit[2]),
                    lineSplit[3],
                    DataServices.stringToDouble(lineSplit[4]),
                    lineSplit[5],
                    lineSplit[6],
                    Integer.parseInt(lineSplit[7])));
        }

        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactionsFromUserID(int userID) {
        List<Transaction> transactions = getAllTransactions();
        List<Transaction> userTransactions = new ArrayList<>();

        for (Transaction t : transactions) {
            if (t.getUserID() == userID) {
                userTransactions.add(t);
            }
        }

        return userTransactions;
    }

    @Override
    public void addTransaction(Transactions transaction) {
        super.appendLine(transaction.addTransactionToCSVFile());
    }
}
