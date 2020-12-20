package mk.ukim.finki.dians.eshop.repository;

import mk.ukim.finki.dians.eshop.model.Market;
import mk.ukim.finki.dians.eshop.model.MarketLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MarketLocationRepository extends JpaRepository<MarketLocation,Long> {
    List<MarketLocation> findMarketLocationsByMarket(Market market);

    List<MarketLocation> findMarketLocationsByLatitudeAndLongitude(float latitude,float longitude);
    List<MarketLocation> findMarketLocationsByAddress(String address);


}
