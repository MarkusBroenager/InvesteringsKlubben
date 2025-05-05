package Comparators;

import Models.Model.Holding;

import java.util.Comparator;

public class SectorComparator implements Comparator<Holding> {
    @Override
    public int compare(Holding o1, Holding o2) {
        if (o1.getSector().compareTo(o2.getSector()) == 0) {
            return o1.getTicker().compareTo(o2.getTicker());
        }
        return o1.getSector().compareTo(o2.getSector());
    }

}
