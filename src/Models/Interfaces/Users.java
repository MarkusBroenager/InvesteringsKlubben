package Models.Interfaces;

public interface Users {
    int getUserID();

    double getInitialCash();

    String getEmail();

    String addToCSVFile();

    @Override
    String toString();
}
