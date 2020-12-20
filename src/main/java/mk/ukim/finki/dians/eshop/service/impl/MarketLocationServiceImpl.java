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

    public MarketLocationServiceImpl(MarketLocationRepository marketLocationRepository) {
        this.marketLocationRepository = marketLocationRepository;
    }

    @Override
    public List<MarketLocation> findMarketLocationsByMarket(Market market) {
        return marketLocationRepository.findMarketLocationsByMarket(market);
    }

    @Override
    public List<MarketLocation> findMarketLocationsByLatitudeAndLongitude(float latitude, float longitude) {
        return marketLocationRepository.findMarketLocationsByLatitudeAndLongitude(latitude,longitude);
    }

    @Override
    public List<MarketLocation> findMarketsLocationByAddress(String address) {
        return marketLocationRepository.findMarketLocationsByAddress(address);
    }

    @Override
    public List<MarketLocation> findAll() {
        return marketLocationRepository.findAll();
    }
}
