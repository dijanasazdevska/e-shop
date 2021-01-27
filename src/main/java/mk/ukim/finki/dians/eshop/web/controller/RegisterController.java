package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.dians.eshop.model.exceptions.PasswordsNotMatchException;
import mk.ukim.finki.dians.eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/register")
@Controller
public class RegisterController {
    //Se generira eden i edinstven interface za registracija.
    private final UserService userService;

    //Constructor
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    //So ovoj metod se dobiva stranata za registriranje.
    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String language, Model model){
        if(language==null){
            language="MK";
        }
        model.addAttribute("language",language);
        return  "register";
    }

    //So ovoj metod se dobiva login stranata po uspesna registracijata na korisnikot na soodvetniot jazik.
    //Dokolku e neuspesen obidot za registracija se prikazuva error.
    @PostMapping
    public String register(HttpServletRequest request, Model model, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword,@RequestParam(required = false) String language ){
if(language==null){
    language="MK";
}
        try {
            userService.register(username, password, repeatPassword, name, surname);
            return "redirect:/login?language="+language;

        }
        catch (InvalidArgumentsException | PasswordsNotMatchException ex){
            model.addAttribute("hasError",true);
            model.addAttribute("error",ex.getMessage());
            return "register";
        }



    }
}
