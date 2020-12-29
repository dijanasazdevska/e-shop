package mk.ukim.finki.dians.eshop.service;


import mk.ukim.finki.dians.eshop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname);



}
