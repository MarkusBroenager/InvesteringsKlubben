public class Controller {

    UserService userService;
    public Controller(UserService u) {
        this.userService = u;
    }

    public void showFrontPage(){
        userService.showFrontPage();
    }
}
