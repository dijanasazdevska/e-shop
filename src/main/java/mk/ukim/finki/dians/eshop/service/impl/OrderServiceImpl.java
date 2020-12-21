package mk.ukim.finki.dians.eshop.service.impl;



import mk.ukim.finki.dians.eshop.model.Order;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.model.ShoppingCart;
import mk.ukim.finki.dians.eshop.model.User;
import mk.ukim.finki.dians.eshop.repository.OrderRepository;
import mk.ukim.finki.dians.eshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(ShoppingCart cart, Product product, User user) {
        return orderRepository.save(new Order(user, product, cart));
    }


    @Override
    public void clearOrders() {
        orderRepository.deleteAll();
    }



    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();
    }
}