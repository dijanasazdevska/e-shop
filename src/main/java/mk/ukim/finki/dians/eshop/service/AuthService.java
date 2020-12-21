package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.ShoppingCart;
import mk.ukim.finki.dians.eshop.model.User;

public interface AuthService {
    public User register(String username, String password, String repeatedPassword, String name, String surname);

    public User login(String username,String password);
    public ShoppingCart findShoppingCart(User user);

}
