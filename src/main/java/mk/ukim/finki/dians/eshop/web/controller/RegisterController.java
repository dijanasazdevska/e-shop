package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.dians.eshop.model.exceptions.PasswordsNotMatchException;
import mk.ukim.finki.dians.eshop.service.AuthService;
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
    private final UserService authService;

    public RegisterController(UserService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String language, Model model){
        if(language==null){
            language="MK";
        }
        model.addAttribute("language",language);
        return  "register";
    }

    @PostMapping
    public String register(HttpServletRequest request, Model model, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword,@RequestParam(required = false) String language ){
if(language==null){
    language="MK";
}
        try {
            authService.register(username, password, repeatPassword, name, surname);
            return "redirect:/login?language="+language;

        }
        catch (InvalidArgumentsException | PasswordsNotMatchException ex){
            model.addAttribute("hasError",true);
            model.addAttribute("error",ex.getMessage());
            return "register";
        }



    }
}
