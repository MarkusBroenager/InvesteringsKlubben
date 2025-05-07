package Services.Interfaces;

import Objects.User;

import java.time.LocalDate;
import java.util.List;

public interface UserServiceInterface {

    List<User> getUsers();

    User getUser(int userID);

    boolean addUser(String fullName, String email, LocalDate dateOfBirth, double initialCash);
}
