package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.Order;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.model.ShoppingCart;
import mk.ukim.finki.dians.eshop.model.User;

public interface ShoppingCartService {
    public Order saveOrder(User user, Product product);
    public ShoppingCart findShoppingCartForUser(User user);
}
