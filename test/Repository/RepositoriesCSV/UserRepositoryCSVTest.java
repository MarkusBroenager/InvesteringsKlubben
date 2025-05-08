package Repository.RepositoriesCSV;

import Models.Model.User;
import Repository.Interfaces.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryCSVTest {

    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryCSV("testUsers.csv");
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
        assertEquals(new User(1, "Maria Jensen", "maria.jensen@email.com", LocalDate.of(1990, 04, 12), 100000, LocalDate.of(2025, 03, 01), LocalDate.of(2025, 03, 01)), userRepository.getUserFromUserID(1));

    }

    @Test
    void addUser() {
    }
}