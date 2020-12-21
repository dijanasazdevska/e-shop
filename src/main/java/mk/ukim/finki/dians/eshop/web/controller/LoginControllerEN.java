package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.model.User;
import mk.ukim.finki.dians.eshop.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.dians.eshop.model.exceptions.UserNotExistsException;
import mk.ukim.finki.dians.eshop.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginControllerEN {
    private final AuthService authService;

    public LoginControllerEN(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping
    public String getLoginPage(){

        return "login";
    }
    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletRequest response, Model model){
        User user =null;
        try{
            user= authService.login(username,password);
            request.getSession().setAttribute("user",user);
            return "redirect:/home";
        }
        catch (UserNotExistsException | InvalidUserCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            request.getSession().setAttribute("user",user);
            return "login";
        }

    }
}
