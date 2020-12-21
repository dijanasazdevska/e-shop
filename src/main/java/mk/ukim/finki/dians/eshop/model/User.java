package mk.ukim.finki.dians.eshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "shop_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
   @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> carts;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.carts=new LinkedList<>();
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<ShoppingCart> getCarts() {
        return carts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", carts=" + carts +
                '}';
    }
}
