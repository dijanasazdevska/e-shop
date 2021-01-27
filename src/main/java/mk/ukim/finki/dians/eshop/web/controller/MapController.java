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
    private final MarketLocationService marketLocationService;
    private final ProductService productService;

    //Constructor
    public MapController(MarketLocationService marketLocationService, ProductService productService) {
        this.marketLocationService = marketLocationService;
        this.productService = productService;
    }

    @GetMapping
    public String getPage(@RequestParam(required = false) String language, Model model, HttpServletRequest request) {
        //Jazikot po default da e na makedonski
        if (language == null)
            language = "MK";
        //Dodavanje atributi za lokacii,jazik,sodrzina,url
        model.addAttribute("marketlocs", marketLocationService.findAll());
        model.addAttribute("language", language);
        model.addAttribute("bodyContent", "map");
        model.addAttribute("url", "/map?language=" + language);
        if (language.equals("MK")) {
            model.addAttribute("newurl", "/map?language=EN");

        } else {
            model.addAttribute("newurl", "/map?language=MK");

        }
        //Setiranje na atributot(jazikot) spored toa koj jazik e odbran/selektiran(MK ili EN)
        request.getSession().setAttribute("language", language);
        return "master-template";
    }

    @PostMapping
    public String postPage(@RequestParam String language, @RequestParam String search, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("products", productService.searchByProducts(search));
        //Prenasocuvanje spored toa koj jazik e odbran/selektiran(MK ili EN)
        return "redirect:/search?language=" + language;
    }


}
