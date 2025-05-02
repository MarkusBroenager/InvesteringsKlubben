package PersistenceLayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class usersReader {

    private Repository repository;
    private final File usersFile = new File("Resources/users.csv");

    public usersReader(Repository repository) {
        this.repository = repository;
    }

    public List<String> getUsersFileContents() {
        return repository.readFile(usersFile);
    }
}
