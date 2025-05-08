package Services.ServicesCSV;

import Models.Model.User;
import Repository.Interfaces.UserRepository;
import Repository.RepositoriesCSV.UserRepositoryCSV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    @BeforeEach
    void setUp() {
        UserRepository userRepository = new UserRepositoryCSV("users.csv");
        userService = new UserService(userRepository);
    }

    @Test
    void getUsers() {

    }

    /*@Test
    void getUser() {

        assertEquals(new User(1, "Maria Jensen", "maria.jensen@email.com", LocalDate.of(1990, 04, 12), 100000, LocalDate.of(2025, 03, 01), LocalDate.of(2025, 03, 01)), userService.getUser(1));
    }*/

    @Test
    void addNewUser() {
    }
}