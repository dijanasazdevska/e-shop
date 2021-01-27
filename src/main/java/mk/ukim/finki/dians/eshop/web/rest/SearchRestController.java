package mk.ukim.finki.dians.eshop.web.rest;

import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchRestController {
    private final ProductService productService;

    public SearchRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{search}")
    public List<Product> getProductsBySearch(@PathVariable String search){
        return productService.searchByProductsByAllNames(search);

    }

}
