package Models.Interfaces;

import java.time.LocalDate;

public interface Asset {

    String getTicker();
    String getName();
    double getPrice();
    String getCurrency();
    String getSector();
    //TODO : unused variable
    LocalDate getLastUpdated();

    default int compareTo(Asset otherAsset){
        if(this.getSector().compareTo(otherAsset.getSector()) == 0){
            return this.getTicker().compareTo(otherAsset.getTicker());
        }else{
            return this.getSector().compareTo(otherAsset.getSector());
        }
    }
}
