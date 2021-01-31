package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.Market;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.repository.MarketRepository;
import mk.ukim.finki.dians.eshop.service.MarketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service za Market
 */


@Service

public class MarketServiceImpl implements MarketService {
    private final MarketRepository marketRepository;

    /**
     * Konstruktor
     * @param marketRepository -Baza na Marketi
     */
    public MarketServiceImpl(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    /**
     * Vrakja lista od site marketi vo marketRepository
     * @return -Go vrakja rezultatot od prebaruvanjeto
     */
    @Override
    public List<Market> findAll() {
        return marketRepository.findAll();
    }

    /**
     * Vrakja lista od marketi spored ime(name) prateno kako argument vo funkcijata
     * @param name - Ime na marketot
     * @return -Go vrakja rezultatot od prebaruvanjeto
     */
    @Override
    public List<Market> findMarketsByName(String name) {
        return marketRepository.findMarketByNameOrNameEN(name,name);
    }

    /**
     * Vrakja lista od produkti na marketot sto go prakjame kako argument vo funkcijata
     * @param market - Ime na marketot
     * @return - Go vrakja rezultatot od prebaruvanjeto
     */
    @Override
    public List<Product> findProductsByMarket(String market) {
        if(findMarketsByName(market).isEmpty()){
            return new ArrayList<>();
        }
        return findMarketsByName(market).get(0).getProducts();
    }


}
