package mk.ukim.finki.dians.eshop.repository;

import mk.ukim.finki.dians.eshop.model.Order;
import mk.ukim.finki.dians.eshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findAllByShoppingCart(ShoppingCart cart);

}
