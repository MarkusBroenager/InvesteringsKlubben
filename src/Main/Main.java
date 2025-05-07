package Main;

import Controller.Controller;
import Objects.User;
import Objects.UserInterface;
import Repositories.Repository;
import Repositories.ReadOnlyRepository;
import Repositories.UserRepositoryCSV;
import Repositories.Interfaces.UserRepositoryInterface;
import Services.UserService;
import Services.Interfaces.UserServiceInterface;

public class Main {
    public static void main(String[] args) {
        UserRepositoryCSV userRepositoryCSV = new UserRepositoryCSV("Users.csv");

        UserService userService = new UserService(userRepositoryCSV);

        Controller controller = new Controller(userService);
        controller.start();







    }
}