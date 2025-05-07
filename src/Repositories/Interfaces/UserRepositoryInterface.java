package Repositories.Interfaces;

import Objects.User;

import java.util.List;

public interface UserRepositoryInterface {
    User getUserFromUserID(int userID);

    List<User> getUser();

    void addUser(User user);

}
