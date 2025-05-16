package Repository.Interfaces;

import Models.Model.Bond;

import java.util.List;

public interface BondRepository {

    List<Bond> getBondList();

    Bond getBondFromTicker(String ticker);

}
