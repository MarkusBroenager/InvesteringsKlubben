import PersistenceLayer.*;
import PresentationLayer.*;
import ServiceLayer.*;

public class Main {

    public static void main(String[] args) {
        //repository layer
        Repository repository = new Repository();
        usersReader usersReader = new usersReader(repository);
        //Buisness and logic layer
        UserService userService = new UserService(usersReader);
        //ServiceLayer.TransactionService transactionService = new ServiceLayer.TransactionService(PersistenceLayer.transactionsReader);

        //COntroller layer
        Controller controller = new Controller(userService);
        //UI layer
        UserInterfaceHandler UIHandler = new UserInterfaceHandler(controller);

        UIHandler.startProgram();


    }
}
