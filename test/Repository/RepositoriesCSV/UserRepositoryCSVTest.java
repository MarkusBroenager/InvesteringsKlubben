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