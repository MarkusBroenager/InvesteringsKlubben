package Repository.Interfaces;

import Models.Model.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();

    User getUserFromUserID(int userID);

    void addUser(User user);
}
