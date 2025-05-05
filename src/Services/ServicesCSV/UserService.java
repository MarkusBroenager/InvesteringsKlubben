package Services.ServicesCSV;

import Models.Interfaces.Users;
import Models.Model.User;
import Repository.Interfaces.UserRepository;
import Services.Interfaces.UserServices;

import java.time.LocalDate;
import java.util.List;

public class UserService implements UserServices {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.getUsers();
    }

    public User getUser(int userID){
        return userRepository.getUserFromUserID(userID);
    }

    public boolean addNewUser(String fullName, String email, LocalDate birthday, double initialCash){
        Users newUser = createNewUser(fullName,email,birthday,initialCash);
        if(newUser.getInitialCash()>=10000){
            userRepository.addUser(newUser);
            return true;
        }
        return false;
    }

    private User createNewUser(String fullName, String email, LocalDate birthday, double initialCash){
        return(new User(getUniqueUserID(),fullName,email,birthday,initialCash,LocalDate.now(),LocalDate.now()));
    }

    private int getUniqueUserID(){
        return 0;
    }


}
