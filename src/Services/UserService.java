package Services;

import Models.User;
import Repository.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private Repository repo;

    public UserService(Repository repo){
        this.repo = repo;
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        List<String> list = repo.readFile();
        list.remove(0);
        for(String line : list){
            String[] lineSplit = line.split(";");
            users.add(new User(Integer.parseInt(lineSplit[0]),lineSplit[1],lineSplit[2],
                    LocalDateCreator.create(lineSplit[3]),Double.parseDouble(lineSplit[4]),
                    LocalDateCreator.create(lineSplit[5]), LocalDateCreator.create(lineSplit[6])));
        }
        return users;
    }

    public boolean addNewUser(String fullName, String email, LocalDate birthday, double initialCash){
        User newUser = createNewUser(fullName,email,birthday,initialCash);
        if(newUser!=null){
            repo.writeLine(newUser.addToCSVFile());
            return true;
        }
        return false;
    }

    private User createNewUser(String fullName, String email, LocalDate birthday, double initialCash){
        return(new User(getUniqueUserID(),fullName,email,birthday,initialCash,LocalDate.now(),LocalDate.now()));
    }

    private int getUniqueUserID(){
        return 0;
    }


}
