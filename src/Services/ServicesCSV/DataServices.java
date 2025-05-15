package Services.ServicesCSV;

import java.time.LocalDate;

public class DataServices {

    public static LocalDate getLocalDate(String date) {
        String[] dateSplit = date.split("-");
        if (dateSplit.length != 3) {
            return LocalDate.of(0, 1, 1);
        }

        try {
            if (Integer.parseInt(dateSplit[2]) > 1900) {
                return LocalDate.of(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[0]));
            } else if (Integer.parseInt(dateSplit[0]) > 1900) {
                return LocalDate.parse(date);
            }
        }catch (NumberFormatException numberFormatException){
            System.out.println("Wrong format! required format is YYYY-MM-DD");
            return LocalDate.of(0, 1, 1);
        }

            return LocalDate.of(0, 1, 1);

    }

    public static double stringToDouble(String getDouble) {
        return Double.parseDouble(getDouble.replace(",", "."));
    }
}
