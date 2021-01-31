package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(name = "searchController",value = "/search")
public class SearchController {
    //Se generira interfejs za prebaruvanje na produkti.
    private final ProductService productService;

    /**
     * Konstruktor
     * @param productService - interfejs za prebaruvanje na produkti.
     */
    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Prebaruvanje na produktot od bazata
     * @param model - Promenliva koja sto gi sodrzi atributite na modelot na stranata.
     * @param request - sozdava objekt HttpServletRequest i go prenesuva kako argument do metodite na usluga.
     * @param language - Jazikot na koj sto treba da se prikaze stranata.
     * @return - Go vrakja glavniot shablon na stranata, preureden spored atributite.
     */
    @GetMapping
    public String getSearch(Model model, HttpServletRequest request,@RequestParam  String language ){

        model.addAttribute("products",request.getSession().getAttribute("products"));
        model.addAttribute("language",language);
        model.addAttribute("bodyContent","search");
        if(language.equals("MK")){
            model.addAttribute("newurl","/search?language=EN");
            model.addAttribute("url","/search?language=MK");

        }
        else{
            model.addAttribute("newurl","/search?language=MK");
            model.addAttribute("url","/search?language=EN");

        }

        request.getSession().setAttribute("language",language);
        return "master-template";
    }

    /**
     * Vrakjanje na rezultatot od prebaruvanjeto
     * @param search - String spored koj se prebaruva za produkt.
     * @param request - HttpServletRequest objekt koj go prenesuva baranjeto za odredena strana do metodot.
     * @param language - Jazikot na koj sto treba da se prikaze stranata.
     * @return - Go vrakja rezultatot od prebaruvanjeto.
     */
    @PostMapping
    public String postSearch(@RequestParam String search , HttpServletRequest request,@RequestParam  String language){
        request.getSession().setAttribute("products",productService.searchByProductsByAllNames(search));

        return "redirect:/search?language="+language;

    }
}
