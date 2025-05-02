package ServiceLayer;
import PersistenceLayer.User;
import PersistenceLayer.usersReader;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    usersReader userReader;
    private List<User> users;
    public UserService(usersReader usersReader){
        this.userReader = usersReader;
        this.users = new ArrayList<>();
    }

    public void startProgram(){
        //initialize users
        List<String> fileContents = userReader.getUsersFileContents();
        User user;
        String line;
        for(int i = 0; i < fileContents.size(); i++){
            line = fileContents.get(i);
            String[] tokens = line.split(";");
            if(tokens.length == 7) {
                user = new User(tokens);
            }else {
                user = null;
                System.out.println("Serious error in format in file users.csv on line: " + (i + 1));
            }
            if(user != null){
                this.users.add(user);
                System.out.println("User added");
            }
        }
        //users initialized
    }

}
