package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.model.User;
import mk.ukim.finki.dians.eshop.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.dians.eshop.model.exceptions.UserNotExistsException;
import mk.ukim.finki.dians.eshop.service.AuthService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Kontroler za login stranata.
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    /**
     * Se generira eden i edinstven interface za najava, registracija i kosnicka za najaveniot korisnik.
     */
    private final AuthService authService;

    /**
     * Constructor so argumenti za Login Kontolerot.
     * @param authService - interface za najava, registracija i kosnicka za najaveniot korisnik.
     */
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * So ovoj metod se dobiva stranata za najava.
     * @param request - sozdava objekt HttpServletRequest i go prenesuva kako argument do metodite na usluga.
     * @param language - jazikot na koj treba da se prikaze stranata.
     * @param model - interfejs koj definira metsto kade se cuvaat atributite na modelot.
     * @return login stranata so izbraniot jazik.
     */
    @GetMapping
    public String getLoginPage(HttpServletRequest request,@RequestParam(required = false) String language,Model model){
if(language==null){
    language="MK";
}
        model.addAttribute("language",language);
        return "login";
    }

    /**
     * So ovoj metod se dobiva Home stranata po uspesna najava na korisnikot na soodvetniot jazik,
     * Dokolku e neuspesen obidot za najava se prikazuva error.
     * @param username - pole kade se cuva privremeno korisnickoto ime na korisnikot.
     * @param password - pole kade se cuva privremeno passwordot na korisnikot.
     * @param request - sozdava objekt HttpServletRequest i go prenesuva kako argument do metodite na usluga.
     * @param response - sozdava objekt HttpServletResponse i go prenesuva kako argument na metodite na usluga.
     * @param model - interfejs koj definira metsto kade se cuvaat atributite na modelot.
     * @param language - jazikot na koj treba da se prikaze stranata.
     * @return login stranata so izbraniot jazik.
     */
    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletRequest response, Model model,@RequestParam(required = false) String language){
if(language==null){
    language="MK";
}
        try{
           User user= authService.login(username,password);

               request.getSession().setAttribute("user",user);

            return "redirect:/?language="+language;
        }
        catch (UserNotExistsException | InvalidUserCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("language",language);
            return "login";
        }

    }
}
