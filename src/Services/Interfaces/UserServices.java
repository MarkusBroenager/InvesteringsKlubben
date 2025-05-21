package Services.Interfaces;

import Models.Model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserServices {

    List<User> getUsers();

    User getUser(int userID);

    int getHighestUserId();

    boolean addNewUser(String fullName, String email, LocalDate birthday, double initialCash);

}
