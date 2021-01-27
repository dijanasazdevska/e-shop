package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.Market;
import mk.ukim.finki.dians.eshop.model.Product;

import java.util.List;

public interface MarketService {
    public List<Market> findAll();
    public List<Market> findMarketsByName(String name);
    public List<Product> findProductsByMarket(String market);


}
