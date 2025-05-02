package Repository.Interfaces;

import Models.Model.User;

import java.util.List;

public interface UserRepository {

    public List<User> getUsers();
    public User getUserFromUserID(int userID);
    public void addUser(User user);
}
