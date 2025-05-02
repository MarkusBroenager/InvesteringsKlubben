package Models.Interfaces;

public interface Users {
    public int getUserID();

    public double getInitialCash();

    public String addToCSVFile();

    @Override
    public String toString();
}
