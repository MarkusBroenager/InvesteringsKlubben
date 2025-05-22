package Repository.RepositoriesCSV;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private Repository repository;

    @BeforeEach
    void setup() {
        repository = new Repository("test\\TestResources\\testUsers.csv");
    }

    @AfterEach
    void cleanup() {
        try (FileOutputStream out = new FileOutputStream("test\\TestResources\\testUsers.csv", false)) {
            out.write(("user_id;full_name;email;birth_date;initial_cash_DKK;created_at;last_updated\n" +
                    "1;Maria Jensen;maria.jensen@email.com;12-04-1990;100000;01-03-2025;01-03-2025\n" +
                    "2;Lars Petersen;lars.p@email.com;19-07-1985;100000;02-03-2025;02-03-2025\n" +
                    "3;Jakob Madsen;jakob.madsen@email.com;03-11-1992;100000;03-03-2025;03-03-2025\n" +
                    "4;Sofie Sørensen;sofie.sorensen@email.com;27-02-1994;100000;04-03-2025;04-03-2025\n" +
                    "5;Anders Holm;anders.holm@email.com;22-05-1988;100000;05-03-2025;05-03-2025\n" +
                    "6;Camilla Kristensen;camilla.k@email.com;09-08-1995;100000;05-03-2025;05-03-2025\n" +
                    "7;Emil Thomsen;emil.thomsen@email.com;14-01-1983;100000;06-03-2025;06-03-2025\n" +
                    "8;Freja Østergaard;freja.ostergaard@email.com;02-12-1991;100000;06-03-2025;06-03-2025\n" +
                    "9;Malthe Lauridsen;malthe.l@email.com;25-09-1986;100000;07-03-2025;07-03-2025\n" +
                    "10;Ida Høeg;ida.hoeg@email.com;17-06-1993;100000;07-03-2025;07-03-2025\n").getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void appendLine() {
        repository.appendLine("test");
        Path path = Paths.get("test\\TestResources\\testUsers.csv");
        String line;
        try (Scanner reader = new Scanner(path)) {
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();
            reader.nextLine();

            line = reader.nextLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals("test", line);
    }
}