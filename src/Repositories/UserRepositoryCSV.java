package Repositories;

import Objects.UserInterface;
import Objects.User;
import Repositories.Interfaces.UserRepositoryInterface;
import Services.DataServices;

import java.util.List;
import java.util.ArrayList;




public class UserRepositoryCSV extends Repository implements UserRepositoryInterface {
    public UserRepositoryCSV(String file) {
        super(file);
    }

    @Override
    public List<User> getUser() {
        List<String> userCSV = super.readFile();
        List<User> users = new ArrayList<>();
        userCSV.removeFirst();
        for (String line : userCSV) {
            String[] lineSplit = line.split(";");
            users.add(new User(Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2],
                    DataServices.getLocalDate(lineSplit[3]), DataServices.stringToDouble(lineSplit[4]),
                    DataServices.getLocalDate(lineSplit[5]), DataServices.getLocalDate(lineSplit[6])));
        }
        return users;
    }

    @Override
    public User getUserFromUserID(int userID) {
        List<User> users = getUser();
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        super.appendLine(user.addToCSVFile());

    }
}
