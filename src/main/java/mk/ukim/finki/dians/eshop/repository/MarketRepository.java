package mk.ukim.finki.dians.eshop.repository;

import mk.ukim.finki.dians.eshop.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MarketRepository  extends JpaRepository<Market,Long> {
List<Market> findMarketsByName(String name);



}
