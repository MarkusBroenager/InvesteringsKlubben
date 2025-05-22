package Repository.RepositoriesCSV;

import Repository.Interfaces.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryCSVTest {

    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryCSV("C:\\Users\\Freja\\IdeaProjects\\InvesteringsKlubben\\test\\TestResources\\testUsers.csv");
    }

    @AfterEach
    void cleanUp(){

    }

    @Test
    void readFile() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void getUserFromUserID_givenValidUserID_returnValidUser() {
        assertEquals(1, userRepository.getUserFromUserID(1).getUserID());

    }

    @Test
    void addUser() {
    }
}