package Repository.Interfaces;

import Models.Model.Bond;

import java.util.List;

public interface BondRepository {

    public List<Bond> getBondList();

    public Bond getBondFromTicker(String ticker);

}
