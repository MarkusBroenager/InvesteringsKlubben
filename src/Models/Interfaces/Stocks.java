package Models.Interfaces;

public interface Stocks {

    String getTicker();

    String getName();

    String getSector();

    String getMarket();

    String getCurrency();

    double getPrice();

    @Override
    String toString();

}
