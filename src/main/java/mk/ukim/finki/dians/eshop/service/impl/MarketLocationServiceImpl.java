package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.Market;
import mk.ukim.finki.dians.eshop.model.MarketLocation;
import mk.ukim.finki.dians.eshop.repository.MarketLocationRepository;
import mk.ukim.finki.dians.eshop.service.MarketLocationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarketLocationServiceImpl implements MarketLocationService {
    private  final MarketLocationRepository marketLocationRepository;
    //Constructor
    public MarketLocationServiceImpl(MarketLocationRepository marketLocationRepository) {
        this.marketLocationRepository = marketLocationRepository;
    }

    @Override
    //Vrakja lista od lokacii na marketi(MarketLocation) spored odreden market praten kako argument vo funkcijata
    public List<MarketLocation> findMarketLocationsByMarket(Market market) {
        return marketLocationRepository.findMarketLocationsByMarket(market);
    }

    @Override
    //Vrakja lista od lokacii na marketi(MarketLocation) spored lat i lon prateni kako argumenti vo funkcijata
    public List<MarketLocation> findMarketLocationsByLatitudeAndLongitude(float latitude, float longitude) {
        return marketLocationRepository.findMarketLocationsByLatitudeAndLongitude(latitude,longitude);
    }

    @Override
    //Vrakja lista od lokacii na marketi(MarketLocation) spored adresata pratena kako argument vo funkcijata
    public List<MarketLocation> findMarketsLocationByAddress(String address) {
        return marketLocationRepository.findMarketLocationsByAddress(address);
    }

    @Override
    //Vrakja lista od site lokacii na  marketi vo marketLocationRepository
    public List<MarketLocation> findAll() {
        return marketLocationRepository.findAll();
    }
}
