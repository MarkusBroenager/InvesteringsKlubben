package Models.Interfaces;

public interface Users {
    int getUserID();

    double getInitialCash();

    String addToCSVFile();

    @Override
    String toString();
}
