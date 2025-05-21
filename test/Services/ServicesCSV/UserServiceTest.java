package Services.ServicesCSV;

import Repository.Interfaces.UserRepository;
import Repository.RepositoriesCSV.UserRepositoryCSV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTest {
//TODO : should this test exist
    private UserService userService;
    @BeforeEach
    void setUp() {
        UserRepository userRepository = new UserRepositoryCSV("users.csv");
        userService = new UserService(userRepository);
    }

    @Test
    void getUsers() {
    }

    @Test
    void addNewUser() {
    }
}