package Repository.RepositoriesCSV;

import Repository.Interfaces.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryCSVTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryCSV("test\\TestResources\\testUsers.csv");
    }

    @Test
    void getUserFromUserID_givenValidUserID_returnValidUser() {
        assertEquals(1, userRepository.getUserFromUserID(1).getUserID());
    }

}