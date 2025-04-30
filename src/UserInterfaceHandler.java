public class UserInterfaceHandler {

    Controller controller;
    public UserInterfaceHandler(Controller controller) {
        this.controller = controller;
    }

    public void startProgram(){
        controller.showFrontPage();
    }

}
