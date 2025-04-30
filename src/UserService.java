public class UserService {

    usersReader userReader;
    public UserService(usersReader usersReader){
        this.userReader = usersReader;
    }

    public void showFrontPage(){
        userReader.readUsers();
    }

}
