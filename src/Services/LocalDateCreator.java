package Services;

import java.time.LocalDate;

public class LocalDateCreator {

    public static LocalDate create(String date){
        String[] dateSplit = date.split("-");
        if(Integer.parseInt(dateSplit[2])>1900){
            return LocalDate.of(Integer.parseInt(dateSplit[2]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[0]));
        }
        return LocalDate.of(Integer.parseInt(dateSplit[0]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[2]));
    }
}
