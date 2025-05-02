package Models.Interfaces;

public interface Stocks {

    public String getTicker();

    public String getName();

    public String getSector();

    public String getMarket();

    public String getCurrency();

    public double getPrice();

    @Override
    public String toString();

}
