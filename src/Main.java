import Models.User;
import Repository.Repository;
import Services.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Repository repo = new Repository("users.csv");
        UserService userService = new UserService(repo);

        printUsers(userService);
        System.out.println("");
        userService.addNewUser("Bob Bobby", "bobbobby00@email.com", LocalDate.of(2000, 12, 25), 100000);
        printUsers(userService);
    }

    public static void printUsers(UserService userService) {
        List<User> users = userService.getUsers();

        for (User user : users) {
            System.out.println(user);
        }
    }
}
