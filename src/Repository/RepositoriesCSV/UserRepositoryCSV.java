package Repository.RepositoriesCSV;

import Models.User;
import Repository.Interfaces.UserRepository;
import Services.ServicesCSV.DataServices;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryCSV extends Repository implements UserRepository {
    public UserRepositoryCSV(String file) {
        super(file);
    }

    @Override
    public List<User> getUsers() {
        List<String> dateCSV = super.readFile();
        List<User> users = new ArrayList<>();
        dateCSV.remove(0);
        for (String line : dateCSV) {
            String[] lineSplit = line.split(";");
            users.add(new User(Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2],
                    DataServices.getLocalDate(lineSplit[3]), DataServices.stringToDouble(lineSplit[4]),
                    DataServices.getLocalDate(lineSplit[5]), DataServices.getLocalDate(lineSplit[6])));
        }
        return users;
    }

    @Override
    public User getUserFromUserID(int userID) {
        List<User> users = getUsers();
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
