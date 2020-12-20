package mk.ukim.finki.dians.eshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy="market")
    List<Product> products;
    private  String imageUrl;
    private String nameEN;

    public Market(Long id, String name , List<Product> products, String imageUrl,String nameEN) {
        this.id = id;
        this.nameEN=nameEN;
        this.name = name;
        this.products = products;
        this.imageUrl = imageUrl;
    }

    public Market() {
    }

    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

}
