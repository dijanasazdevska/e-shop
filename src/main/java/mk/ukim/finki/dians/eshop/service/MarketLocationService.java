package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.Market;
import mk.ukim.finki.dians.eshop.model.MarketLocation;

import java.util.List;

public interface MarketLocationService {

    List<MarketLocation> findMarketLocationsByMarket(Market market);
   List<MarketLocation> findMarketLocationsByLatitudeAndLongitude(float latitude, float longitude);
    List<MarketLocation> findMarketsLocationByAddress(String address);

    List<MarketLocation> findAll();
}
