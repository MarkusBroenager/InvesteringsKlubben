package Comparators;

import Models.Interfaces.Portfolios;

import java.util.Comparator;

public class profitAndLossInDKKComparator implements Comparator<Portfolios> {
    @Override
    public int compare(Portfolios o1, Portfolios o2) {
        return (int) ((o2.getProfitOrLossInDKK() * 100) - (o1.getProfitOrLossInDKK() * 100));
    }
}
