package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.Order;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.model.ShoppingCart;
import mk.ukim.finki.dians.eshop.model.User;

import java.util.List;

/**Interfejsot OrderService sodrzi lista od naracki i lista na naracki od korisnik.
 *Ovoj interfejs ima za cel da ja postavi narackata na korisnikot i ima opcija korisnikot da ja izbrise narackata.
 */
public interface OrderService {
    Order placeOrder(ShoppingCart cart, Product product, User user);
    List<Order> listAll();
    public void clearOrders();
    List<Order> findOrdersByUser(User user);
    void deleteOrder(Long id);
}
