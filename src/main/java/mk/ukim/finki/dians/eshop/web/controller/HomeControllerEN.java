package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.service.MarketService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeControllerEN {
    private final MarketService marketService;
    private final ProductService productService;

    public HomeControllerEN(MarketService marketService, ProductService productService) {
        this.marketService = marketService;
        this.productService = productService;
    }

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("markets",marketService.findAll());
        model.addAttribute("language","EN");

        return "home";
    }
    @PostMapping
    public String search(@RequestParam String search, HttpServletRequest request){


        request.getSession().setAttribute("products",productService.searchByProductsEN(search));
        return "redirect:/search";

    }
}
