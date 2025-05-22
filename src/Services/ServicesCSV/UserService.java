package Services.ServicesCSV;

import Models.Model.User;
import Repository.Interfaces.UserRepository;
import Services.Interfaces.UserServices;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class UserService implements UserServices {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUser(int userID) {
        return userRepository.getUserFromUserID(userID);
    }

    @Override
    public boolean addNewUser(String fullName, String email, LocalDate birthday, double initialCash) {
        for (User u : getUsers()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        User newUser = createNewUser(fullName, email, birthday, initialCash);
        userRepository.addUser(newUser);
        return true;
    }

    public int getHighestUserId() {
        List<User> users = getUsers();
        Collections.sort(users);
        return users.get(users.size() - 1).getUserID();
    }

    private User createNewUser(String fullName, String email, LocalDate birthday, double initialCash) {
        return (new User(getUniqueUserID(), fullName, email, birthday, initialCash, LocalDate.now(), LocalDate.now()));
    }

    private int getUniqueUserID() {
        return getHighestUserId() + 1;
    }


}
