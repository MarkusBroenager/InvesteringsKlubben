package Comparators;

import Services.ServicesCSV.DataServices;

import java.util.Comparator;

public class sortSectorByTotalValueOfPortfolio implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String first = o1.replace("%", ";");
        String second = o2.replace("%", ";");
        String[] splitFirst = first.split(";");
        String[] splitSecond = second.split(";");
        double firstSectorValue = DataServices.stringToDouble(splitFirst[1]);
        double secondSectorValue = DataServices.stringToDouble(splitSecond[1]);
        return (int) ((secondSectorValue * 100) - (firstSectorValue * 100));
    }
}
