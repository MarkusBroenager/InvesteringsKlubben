package Services.Interfaces;

import Models.User;

import java.time.LocalDate;
import java.util.List;

public interface UserServices {

    public List<User> getUsers();

    public User getUser(int userID);

    public boolean addNewUser(String fullName, String email, LocalDate birthday, double initialCash);

}
