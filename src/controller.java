import java.util.ArrayList;
import java.util.Scanner;

public class controller {

    public static void startProgramme() {

        boolean programmeIsRunning = true;
        ArrayList<Users> listOfUsers = new ArrayList<>();
        Users.loadUsersData(listOfUsers);
        while(programmeIsRunning) {
        System.out.println("Tast 1 hvis du er leder, tast 2 hvis du er medlem");
        Scanner scanner = new Scanner(System.in);

        int input1 = scanner.nextInt();

        if (input1 == 1) {
            System.out.println("Tast:");
            System.out.println("1. Opret bruger");
            System.out.println("2. Se liste over brugere");

        } int input2 = scanner.nextInt();

        if (input2 == 1) {

            Users.createUser(listOfUsers);

        } else if (input2 == 2) {
            Users.showListOfUsers(listOfUsers);

        } else if (input2 == 5) {
            programmeIsRunning = false;
        }
        }
    }
}
