package mk.ukim.finki.dians.eshop.web.rest;

import mk.ukim.finki.dians.eshop.model.Market;
import mk.ukim.finki.dians.eshop.model.MarketLocation;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.service.MarketLocationService;
import mk.ukim.finki.dians.eshop.service.MarketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MarketRestController {
    private final MarketService marketService;
    private final MarketLocationService marketLocationService;

    public MarketRestController(MarketService marketService, MarketLocationService marketLocationService) {
        this.marketService = marketService;

        this.marketLocationService = marketLocationService;
    }

    @GetMapping("/markets")
    public List<Market> getMarkets() {
        return marketService.findAll();

    }


    @GetMapping("/markets/{market}")
    public List<Product> getProductsByMarket(@PathVariable String market) {
        return marketService.findProductsByMarket(market);
    }

    @GetMapping("/marketlocations/{market}")
    public List<MarketLocation> getLocations(@PathVariable String market) {
        if(marketService.findMarketsByName(market).isEmpty())
            return new  ArrayList<>();
        return marketLocationService.findMarketLocationsByMarket(marketService.findMarketsByName(market).get(0));
    }
}

