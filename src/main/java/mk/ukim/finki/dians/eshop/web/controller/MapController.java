package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.service.MarketLocationService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
@RequestMapping("/map")
public class MapController {
    //Se generira interfejs za prebaruvanje na lokacii na marketi
    private final MarketLocationService marketLocationService;
    //Se generira interfejs za prebaruvanje na produkti.
    private final ProductService productService;

    /**
     * Konstruktor
     * @param marketLocationService interfejs za prebaruvanje na lokacii na marketi
     * @param productService interfejs za prebaruvanje na produkti.
     */
    public MapController(MarketLocationService marketLocationService, ProductService productService) {
        this.marketLocationService = marketLocationService;
        this.productService = productService;
    }

    /**
     *  Se dobiva baranata strana
     * @param language - Jazikot na koj sto treba da se prikaze stranata.
     * @param model -  Promenliva koja sto gi sodrzi atributite na modelot na stranata.
     * @param request - HttpServletRequest objekt koj go prenesuva baranjeto za odredena strana do metodot.
     * @return Go vrakja glavniot shablon na stranata, preureden spored atributite.
     */
    @GetMapping
    public String getPage(@RequestParam(required = false) String language, Model model, HttpServletRequest request) {

        if (language == null)
            language = "MK";
        model.addAttribute("marketlocs", marketLocationService.findAll());
        model.addAttribute("language", language);
        model.addAttribute("bodyContent", "map");
        model.addAttribute("url", "/map?language=" + language);
        if (language.equals("MK")) {
            model.addAttribute("newurl", "/map?language=EN");
        } else {
            model.addAttribute("newurl", "/map?language=MK");
        }
        request.getSession().setAttribute("language", language);
        return "master-template";

    }

    /**
     * Vrakja rezultat od baranjeto za strana
     * @param language - Jazikot na koj sto treba da se prikaze stranata.
     * @param search - String spored koj se prebaruva za produkt.
     * @param request - HttpServletRequest objekt koj go prenesuva baranjeto za odredena strana do metodot.
     * @param response - sozdava objekt HttpServletResponse i go prenesuva kako argument na metodite na usluga.
     * @return - Go vrakja rezultatot od prebaruvanjeto.
     */
    @PostMapping
    public String postPage(@RequestParam String language, @RequestParam String search, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("products", productService.searchByProducts(search));
        return "redirect:/search?language=" + language;
    }


}
