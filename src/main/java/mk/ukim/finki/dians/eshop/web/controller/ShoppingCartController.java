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
    //Se generira eden i ednistven interfejs za prebaruvanje na produkti.
    private final ProductService productService;
    private final UserRepository userRepository;

    //Constructor
    public ShoppingCartController(OrderService orderService, ShoppingCartService shoppingCartService, ProductService productService, UserRepository userRepository) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.userRepository = userRepository;
    }

    //Se dobiva strana za pregled na site narachki vo koshnichkata.
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

    //Metod za brishenje na narachka od koshnichkata spored ID.
    @GetMapping("/delete/{id}")
    public String deleteFromCart(@RequestParam String language,@PathVariable Long id){
        orderService.deleteOrder(id);
        return "redirect:/shopping-cart?language="+language;

    }

    //Metod za dodavanje na narachka od koshnichkata.
    @RequestMapping("/add/{category}/{id}")
    public String addToCart( @RequestParam  String language, @PathVariable Long id,@PathVariable String category,HttpServletRequest request){
        User user=userRepository.findByUsername(request.getRemoteUser()).get();
        shoppingCartService.saveOrder(user,productService.searchProductById(id));
        return "redirect:/category/{category}?language="+language;
    }

    //Se dobiva produkti spored vnesenata vrednost vo poleto za prebaruvanje.
    @PostMapping("/shopping-cart")
    public String search(@RequestParam String language, @RequestParam String search, HttpServletRequest request){


        request.getSession().setAttribute("products",productService.searchByProducts(search));
        return "redirect:/search?language="+language;

    }

}
