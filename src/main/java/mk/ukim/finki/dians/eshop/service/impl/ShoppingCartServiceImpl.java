package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.Order;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.model.ShoppingCart;
import mk.ukim.finki.dians.eshop.model.User;
import mk.ukim.finki.dians.eshop.repository.OrderRepository;
import mk.ukim.finki.dians.eshop.repository.ShoppingCartRepository;
import mk.ukim.finki.dians.eshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    //Se generira interfejs za prebaruvanje na koshnichka od bazata na koshnichki za odreden korisnik.
    private final ShoppingCartRepository shoppingCartRepository;
    //Se generira interfejs za prebaruvanje na narachki od bazata na narachki za odreden korisnik.
    private final OrderRepository orderRepository;

    /**
     * Konstruktor
     * @param shoppingCartRepository - interfejs za prebaruvanje na koshnichka od bazata na koshnichki za odreden korisnik.
     * @param orderRepository - interfejs za prebaruvanje na narachki od bazata na narachki za odreden korisnik.
     */
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, OrderRepository orderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Se generira narachka na tekovniot korisnik so izbranite produkti.
     * @param user - Promenliva koja sto go posochuva momentalniot korisnik.
     * @param product - Produkt koj e izbran za da se naracha.
     * @return - Ja vrakja koshnichkata na momentalniot korisnik so dodadena narachka od izbranite produkti.
     */
    @Override
    public Order saveOrder(User user, Product product) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user);
        if(shoppingCart==null){
            shoppingCartRepository.save(new ShoppingCart(user));
        }
        shoppingCart=shoppingCartRepository.findByUser(user);

        Order o = new Order(user,product,shoppingCart);
        orderRepository.save(o);
        return  shoppingCart.addOrder(o);



    }

    /**
     * Se dobiva koshnicka od narachki za odredeniot korisnik.
     * @param user - Promenliva koja sto go posochuva momentalniot korisnik.
     * @return - Ja vrakja koshnichkata na momentalniot korisnik po nejzino prebaruvanje od bazata.
     */
    @Override
    public ShoppingCart findShoppingCartForUser(User user) {
        return shoppingCartRepository.findByUser(user);
    }
}

