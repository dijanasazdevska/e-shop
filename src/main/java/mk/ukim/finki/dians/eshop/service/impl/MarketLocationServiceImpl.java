package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.Market;
import mk.ukim.finki.dians.eshop.model.MarketLocation;
import mk.ukim.finki.dians.eshop.repository.MarketLocationRepository;
import mk.ukim.finki.dians.eshop.service.MarketLocationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service za lokacija na market
 */

@Service
public class MarketLocationServiceImpl implements MarketLocationService {
    private  final MarketLocationRepository marketLocationRepository;

    /**
     * Konstruktor
     * @param marketLocationRepository - Baza na lokacii na marketi
     */
    public MarketLocationServiceImpl(MarketLocationRepository marketLocationRepository) {
        this.marketLocationRepository = marketLocationRepository;
    }

    /**
     * So ovoj metod se naogja lokacijata na marketot spored negovoto ime,
     * Potrebno e korisnikot da go vnese imeto na marketot
     * @param market - Marketot koj treba da se prebara
     * @return -Go vrakja rezultatot od prebaruvanjeto
     */
    @Override
    public List<MarketLocation> findMarketLocationsByMarket(Market market) {
        return marketLocationRepository.findMarketLocationsByMarket(market);
    }

    /**
     * Vrakja lista od lokacii na marketi(MarketLocation) spored lat i lon prateni kako argumenti vo funkcijata
     * @param latitude - latituda na baraniot market
     * @param longitude -longituda na barantiot market
     * @return - Go vrakja rezultatot od prebaruvanjeto
     */
    @Override
    public List<MarketLocation> findMarketLocationsByLatitudeAndLongitude(float latitude, float longitude) {
        return marketLocationRepository.findMarketLocationsByLatitudeAndLongitude(latitude,longitude);
    }

    /**
     * Vrakja lista od lokacii na marketi(MarketLocation) spored adresata pratena kako argument vo funkcijata
     * @param address - adresa na baraniot market
     * @return - Go vrakja rezultatot od prebaruvanjeto
     */
    @Override
    public List<MarketLocation> findMarketsLocationByAddress(String address) {
        return marketLocationRepository.findMarketLocationsByAddress(address);
    }

    /**
     * //Vrakja lista od site lokacii na  marketi vo marketLocationRepository
     * @return - Go vrakja rezultatot od prebaruvanjeto
     */
    @Override
    public List<MarketLocation> findAll() {
        return marketLocationRepository.findAll();
    }
}
