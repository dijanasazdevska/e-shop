package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.Market;
import mk.ukim.finki.dians.eshop.repository.MarketRepository;
import mk.ukim.finki.dians.eshop.service.MarketService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarketServiceImpl implements MarketService {
    private final MarketRepository marketRepository;

    public MarketServiceImpl(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    @Override
    public List<Market> findAll() {
        return marketRepository.findAll();
    }

    @Override
    public List<Market> findMarketsByName(String name) {
        return marketRepository.findMarketsByName(name);
    }




}
