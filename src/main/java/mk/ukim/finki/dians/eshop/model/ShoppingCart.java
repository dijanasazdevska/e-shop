package mk.ukim.finki.dians.eshop.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private LocalDateTime dateCreated;
    @OneToMany(mappedBy = "shoppingCart")
    private List<Order> orders;

    public ShoppingCart(User user) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.orders = new LinkedList<>();
    }

    public ShoppingCart() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public Order addOrder(Order o){
        orders.add(o);
        return o;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +

                ", dateCreated=" + dateCreated +
                ", orders=" + orders +
                '}';
    }
}
