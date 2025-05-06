package Comparators;

import Models.Model.PortfolioDKK;

import java.util.Comparator;

public class profitAndLossInDKKComparator implements Comparator<PortfolioDKK> {
    @Override
    public int compare(PortfolioDKK o1, PortfolioDKK o2) {
        return (int) ((o2.getProfitOrLossInDKK() * 100) - (o1.getProfitOrLossInDKK() * 100));
    }
}
