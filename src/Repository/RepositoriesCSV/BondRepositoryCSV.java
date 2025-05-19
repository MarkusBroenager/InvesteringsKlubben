package Repository.RepositoriesCSV;

import Models.Model.Bond;
import Repository.Interfaces.BondRepository;
import Services.ServicesCSV.DataServices;

import java.util.ArrayList;
import java.util.List;

public class BondRepositoryCSV extends ReadOnlyRepository implements BondRepository {


    public BondRepositoryCSV(String fileName){
        super(fileName);
    }

    @Override
    public List<Bond> getBondList(){
        List<Bond> bonds = new ArrayList<>();
        List<String> list = super.readFile();
        list.remove(0);
        for (String line : list) {
            String[] lineSplit = line.split(";");
            bonds.add(new Bond(lineSplit[0],
                    lineSplit[1],
                    DataServices.stringToDouble(lineSplit[2]),
                    lineSplit[3],
                    DataServices.stringToDouble(lineSplit[4]),
                    DataServices.getLocalDate(lineSplit[5]),
                    DataServices.getLocalDate(lineSplit[6]),
                    lineSplit[7],
                    lineSplit[8],
                    DataServices.getLocalDate(lineSplit[9])));
        }
        return bonds;
    }



    //TODO: Make exception?
    @Override
    public Bond getBondFromTicker(String ticker){
        List<Bond> bonds = getBondList();
        for (Bond bond : bonds) {
            if (bond.getTicker().equalsIgnoreCase(ticker)) {
                return bond;
            }
        }
        return null;
    }

}
