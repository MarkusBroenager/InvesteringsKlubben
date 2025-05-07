package Services;

import Objects.User;
import Objects.UserInterface;
import Repositories.UserRepositoryCSV;
import Repositories.Interfaces.UserRepositoryInterface;
import Services.Interfaces.UserServiceInterface;

import java.time.LocalDate;
import java.util.List;

public class UserService implements UserServiceInterface {

    private UserRepositoryCSV userRepositoryCSV;

    public UserService(UserRepositoryCSV userRepositoryCSV) {
        this.userRepositoryCSV = userRepositoryCSV;
    }

    public List<User> getUsers() {
        return userRepositoryCSV.getUser();
    }

    public User getUser(int userID) {
        return userRepositoryCSV.getUserFromUserID(userID);
    }

    public boolean addUser(String fullName, String email, LocalDate dateOfBirth, double initialCash) {
        User newUser = createNewUser(fullName, email, dateOfBirth, initialCash);
        if (newUser.getInitialCash() >= 10000) {
            userRepositoryCSV.addUser(newUser);
            return true;
        }
        return false;
    }

    private User createNewUser(String fullName, String email, LocalDate dateOfBirth, double initialCash) {
        return(new User(getUniqueUserID(), fullName, email, dateOfBirth, initialCash, LocalDate.now(), LocalDate.now()));
    }

    private int getUniqueUserID() {
        return 0;
    }
}
