package Services.ServicesCSV;

import java.time.LocalDate;

public class DataServices {

    public static LocalDate getLocalDate(String date){
        String[] dateSplit = date.split("-");
        if(Integer.parseInt(dateSplit[2])>1900){
            return LocalDate.of(Integer.parseInt(dateSplit[2]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[0]));
        }
        return LocalDate.parse(date);
    }

    public static double stringToDouble(String getDouble) {
        return Double.parseDouble(getDouble.replace(",", "."));
    }
}
