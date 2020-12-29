package mk.ukim.finki.dians.eshop.model;

import lombok.Data;
import mk.ukim.finki.dians.eshop.model.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "shop_user")
public class User implements UserDetails {
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
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.carts=new LinkedList<>();
        this.role=Role.ROLE_USER;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCarts(List<ShoppingCart> carts) {
        this.carts = carts;
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
