package Comparators;

import Models.Model.PortfolioDKK;

import java.util.Comparator;

public class ProfitAndLossInPercentageComparator implements Comparator<PortfolioDKK> {


    @Override
    public int compare(PortfolioDKK o1, PortfolioDKK o2) {
        return (int) ((o2.getProfitOrLossInPercentage() * 10000) - (o1.getProfitOrLossInPercentage() * 10000));
    }
}
