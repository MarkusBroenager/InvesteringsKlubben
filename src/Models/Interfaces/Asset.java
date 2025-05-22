package Models.Interfaces;

public interface Asset {

    String getTicker();
    String getName();
    double getPrice();
    String getCurrency();
    String getSector();

    default int compareTo(Asset otherAsset){
        if(this.getSector().compareTo(otherAsset.getSector()) == 0){
            return this.getTicker().compareTo(otherAsset.getTicker());
        }else{
            return this.getSector().compareTo(otherAsset.getSector());
        }
    }
}
