package ServiceLayer;

public class Controller {

    UserService userService;
    public Controller(UserService u) {
        this.userService = u;
    }

    public void startUserProgram(){
        userService.startProgram();
    }
}
