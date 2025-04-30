public class Main {

    public static void main(String[] args) {
        //repository layer
        usersReader usersReader = new usersReader();
        //Buisness and logic layer
        UserService userService = new UserService(usersReader);
        //TransactionService transactionService = new TransactionService(transactionsReader);

        //COntroller layer
        Controller controller = new Controller(userService);
        //UI layer
        UserInterfaceHandler UIHandler = new UserInterfaceHandler(controller);

        UIHandler.startProgram();


    }
}
