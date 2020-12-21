package mk.ukim.finki.dians.eshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="shop_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    User user;
    @ManyToOne
    Product product;
    @ManyToOne
    ShoppingCart shoppingCart;


    public Order(User user, Product product, ShoppingCart shoppingCart) {
        this.user = user;
        this.product = product;
        this.shoppingCart = shoppingCart;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
