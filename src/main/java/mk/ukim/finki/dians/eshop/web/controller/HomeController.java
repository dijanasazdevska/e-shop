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
@RequestMapping("/насловна")
public class HomeController {
    private final MarketService marketService;
    private final ProductService productService;

    public HomeController(MarketService marketService, ProductService productService) {
        this.marketService = marketService;
        this.productService = productService;
    }

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("markets",marketService.findAll());
        model.addAttribute("language","MK");

        return "home";
    }
    @PostMapping
    public String search( @RequestParam String search, HttpServletRequest request){


        request.getSession().setAttribute("products",productService.searchByProducts(search));
        return "redirect:/searchMK";

    }
}
