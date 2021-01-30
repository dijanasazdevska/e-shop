package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.model.User;
import mk.ukim.finki.dians.eshop.repository.UserRepository;
import mk.ukim.finki.dians.eshop.service.OrderService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import mk.ukim.finki.dians.eshop.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class ShoppingCartController {
    //Se generira interfejs za site uslugi potrebni za edna narachka.
    private final OrderService orderService;
    //Se generira interfejs za zacuvuvanje i pronaogjanje na narachka vo odredena koshnichka.
    private final ShoppingCartService shoppingCartService;
    //Se generira interfejs za prebaruvanje na produkti.
    private final ProductService productService;
    //Se generira interfejs za prebaruvanje na korisnici od bazata na korisnici.
    private final UserRepository userRepository;

    /**
     * Konstruktor
     * @param orderService - interfejs za site uslugi potrebni za edna narachka.
     * @param shoppingCartService - interfejs za zacuvuvanje i pronaogjanje na narachka vo odredena koshnichka.
     * @param productService - interfejs za prebaruvanje na produkti.
     * @param userRepository - interfejs za prebaruvanje na korisnici od bazata na korisnici.
     */
    public ShoppingCartController(OrderService orderService, ShoppingCartService shoppingCartService, ProductService productService, UserRepository userRepository) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.userRepository = userRepository;
    }

    /**
     * Se dobiva strana za pregled na site narachki vo koshnichkata.
     * @param model - Promenliva koja sto gi sodrzi atributite na modelot na stranata.
     * @param request - HttpServletRequest objekt koj go prenesuva baranjeto za odredena strana do metodot.
     * @param language - Jazikot na koj sto treba da se prikaze stranata.
     * @return - Go vrakja glavniot shablon na stranata, preureden spored atributite.
     */
    @GetMapping("/shopping-cart")
    public String getPage(Model model, HttpServletRequest request, @RequestParam(required = false) String language){
    if(language==null)
        language="MK";

            model.addAttribute("orders",orderService.findOrdersByUser(userRepository.findByUsername(request.getRemoteUser()).get()));
            model.addAttribute("language",language);


        model.addAttribute("bodyContent","shoppingCart");
        if(language.equals("MK"))
            model.addAttribute("newurl","/shopping-cart?language=EN");
        else
            model.addAttribute("newurl","/shopping-cart?language=MK");
        model.addAttribute("url","/shopping-cart?language="+language);

        return "master-template";

    }

    /**
     * Metod za brishenje na narachka od koshnichkata spored ID.
     * @param language - Jazikot na koj sto treba da se prikaze stranata.
     * @param id - ID broj na narachka koja treba da se izbrishe od koshnichka.
     * @return - Vrakja preuredena koshnichka posle brishenjeto na narachkata.
     */
    @GetMapping("/delete/{id}")
    public String deleteFromCart(@RequestParam String language,@PathVariable Long id){
        orderService.deleteOrder(id);
        return "redirect:/shopping-cart?language="+language;

    }

    /**
     * Metod za dodavanje na narachka od koshnichkata.
     * @param language - Jazikot na koj sto treba da se prikaze stranata.
     * @param id - ID broj na narachka koja treba da se dodade vo koshnichka.
     * @param category - Kategorijata vo koja spagja produktot.
     * @param request - HttpServletRequest objekt koj go prenesuva baranjeto za odredena strana do metodot.
     * @return - Vrakja preuredena koshnichka posle dodavanjeto na narachkata.
     */
    @RequestMapping("/add/{category}/{id}")
    public String addToCart( @RequestParam  String language, @PathVariable Long id,@PathVariable String category,HttpServletRequest request){
        User user=userRepository.findByUsername(request.getRemoteUser()).get();
        shoppingCartService.saveOrder(user,productService.searchProductById(id));
        return "redirect:/category/{category}?language="+language;
    }

    /**
     * Se dobiva produkti spored vnesenata vrednost vo poleto za prebaruvanje.
     * @param language - Jazikot na koj sto treba da se prikaze stranata.
     * @param search - String spored koj se prebaruva za produkt.
     * @param request - HttpServletRequest objekt koj go prenesuva baranjeto za odredena strana do metodot.
     * @return - Go vrakja rezultatot od prebaruvanjeto.
     */
    @PostMapping("/shopping-cart")
    public String search(@RequestParam String language, @RequestParam String search, HttpServletRequest request){


        request.getSession().setAttribute("products",productService.searchByProducts(search));
        return "redirect:/search?language="+language;

    }

}
