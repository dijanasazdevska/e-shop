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
@RequestMapping("/")
public class HomeController {
    //Se generira eden i ednistven interfejs za prebaruvanje na marketi.
    private final MarketService marketService;
    //Se generira eden i ednistven interfejs za prebaruvanje na produkti.
    private final ProductService productService;

    //Constructor
    public HomeController(MarketService marketService, ProductService productService) {
        this.marketService = marketService;
        this.productService = productService;
    }

    //Se dobiva pochetnata strana so izbraniot jazik.
    @GetMapping
    public String getHomePage(@RequestParam(required = false) String language, Model model,HttpServletRequest request){
        if(language==null)
            language="MK";
        model.addAttribute("markets",marketService.findAll());
        model.addAttribute("language",language);
        model.addAttribute("bodyContent","home");
        if(language.equals("MK"))
        model.addAttribute("newurl","/?language=EN");
        else
            model.addAttribute("newurl","/?language=MK");
        model.addAttribute("url","/?language="+language);
        request.getSession().setAttribute("language",language);

        return "master-template";
    }

    //Se dobiva produkti spored vnesenata vrednost vo poleto za prebaruvanje.
    @PostMapping
    public String search(@RequestParam String language, @RequestParam String search, HttpServletRequest request){


        request.getSession().setAttribute("products",productService.searchByProducts(search));
        return "redirect:/search?language="+language;

    }
}
