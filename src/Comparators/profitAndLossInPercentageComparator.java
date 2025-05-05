package Comparators;

import Models.Interfaces.Portfolios;

import java.util.Comparator;

public class profitAndLossInPercentageComparator implements Comparator<Portfolios> {


    @Override
    public int compare(Portfolios o1, Portfolios o2) {
        return (int) ((o2.getProfitOrLossInPercentage() * 10000) - (o1.getProfitOrLossInPercentage() * 10000));
    }
}
