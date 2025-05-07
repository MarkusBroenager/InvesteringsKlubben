package Controller;

import Objects.User;
import Objects.UserInterface;
import Repositories.Interfaces.UserRepositoryInterface;
import Repositories.UserRepositoryCSV;
import Services.Interfaces.UserServiceInterface;
import Services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private UserService userService;
    private Scanner scanner = new Scanner(System.in);

    public Controller(UserService userService) {
        this.userService = userService;
    }

    public void start() {
        boolean isRunning = true;
        System.out.println("Welcome to the Investment Club.");
        while (isRunning) {
            System.out.println("0 - Exit, 1 - Member Menu, 2 - Leader Menu");
            int userChoice = getUserChoice(2);
            switch (userChoice) {
                case 1:
                    memberUI();
                    break;
                case 2:
                    leaderUI();
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }


    private int getUserChoice(int choiceUpperBoundary) {
        int userInput;

        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.scanner.hasNextInt()) {

                this.scanner.next();

            }
            userInput = this.scanner.nextInt();
            this.scanner.nextLine();
        } while (userInput > choiceUpperBoundary || userInput < 0);

        return userInput;
    }


}
