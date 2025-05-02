package Models.Interfaces;

public interface Stocks {

    public String getTicker();

    public String getName();

    public String getSector();

    public String getMarket();

    public String getCurrency();

    @Override
    public String toString();

}
