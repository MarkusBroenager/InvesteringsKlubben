package Comparators;

import Models.Model.Holding;

import java.util.Comparator;

public class HoldingSortBySector implements Comparator<Holding> {
    @Override
    public int compare(Holding o1, Holding o2) {
        return o1.getSector().compareTo(o2.getSector());
    }
}
