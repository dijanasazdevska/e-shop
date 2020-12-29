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
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderRepository orderRepository;


    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, OrderRepository orderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.orderRepository = orderRepository;
    }

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

    @Override
    public ShoppingCart findShoppingCartForUser(User user) {
        return shoppingCartRepository.findByUser(user);
    }
}

