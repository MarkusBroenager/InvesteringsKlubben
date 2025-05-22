package Services.ServicesCSV;

public class ColorService {

    private static final String BLUE_COLOR = "\u001B[34m";
    private static final String STANDARD_COLOR = "\u001B[0m";
    private static final String RED_BACKGROUND_COLOR = "\u001B[41m";
    public static String getBlueColor(){
        return BLUE_COLOR;
    }
    public static String getStandardColor(){
        return STANDARD_COLOR;
    }
    public static String getRedBackgroundColor(){
        return RED_BACKGROUND_COLOR;
    }

    public static String colorText(String text, String color){
        return color + text + STANDARD_COLOR;
    }

}
