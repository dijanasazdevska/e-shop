package mk.ukim.finki.dians.eshop.repository;


import mk.ukim.finki.dians.eshop.model.ShoppingCart;
import mk.ukim.finki.dians.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
public ShoppingCart findByUser(User user);
public List<ShoppingCart> findShoppingCartsByUser(User user);


}
