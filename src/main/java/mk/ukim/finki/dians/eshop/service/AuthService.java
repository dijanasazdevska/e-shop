package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.ShoppingCart;
import mk.ukim.finki.dians.eshop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    public User register(String username, String password, String repeatedPassword, String name, String surname);

    public User login(String username,String password);
    public ShoppingCart findShoppingCart(User user);
    public User addUser();
    public void changeUser(User user,User newUser);


}
