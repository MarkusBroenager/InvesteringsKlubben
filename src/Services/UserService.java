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
                    createLocalDateFromString(lineSplit[3]),Double.parseDouble(lineSplit[4]),
                    createLocalDateFromString(lineSplit[5]),createLocalDateFromString(lineSplit[6])));
        }
        return users;
    }

    private LocalDate createLocalDateFromString(String date){
        String[] dateSplit = date.split("-");
        return LocalDate.of(Integer.parseInt(dateSplit[2]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[0]));
    }

    private User createNewUser(String fullName, String email, LocalDate birthday, double initialCash){

    }

    private int getUniqueUserID(){

    }


}
