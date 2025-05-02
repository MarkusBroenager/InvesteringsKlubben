package PresentationLayer;

import ServiceLayer.Controller;
import java.util.Scanner;
public class UserInterfaceHandler {

    Controller controller;
    Scanner consoleScanner;
    public UserInterfaceHandler(Controller controller) {
        this.controller = controller;
        this.consoleScanner = new Scanner(System.in);
    }

    public void startProgram(){
        System.out.println("Velkommen til 'Investorerne'\n\nHold styr på alle dine investeringer et sted!\n\n\nØnsker " +
                "du at logge ind som #Medlem# eller #Admin#\ntast 0 for at lukke programmet, tast 1 for medlem, tast 2" +
                " for admin");
        switch (getInputInt(2, 0)){
            case 0 :
                //exit
                break;
            case 1 :
                controller.startUserProgram();
                memberFrontPage();
                break;
            case 2 :
                //other menu
                break;
            default:
                throw new RuntimeException();
        }

    }

    private void memberFrontPage(){
        System.out.println("Velkommen bruger Hvad ønsker du at se?");
    }

    private int getInputInt(int choiceUpperBoundary) {
    return getInputInt(choiceUpperBoundary, 1);
    }
    private int getInputInt(int choiceUpperBoundary, int choiceLowerBoundary){
        int result;
        do {
            while (!consoleScanner.hasNextInt()) {
                consoleScanner.next();
            }
            result = consoleScanner.nextInt();
        }while((result > choiceUpperBoundary || result < choiceLowerBoundary));
        return result;
    }

}
