package mk.ukim.finki.dians.eshop.model;
import lombok.Data;
import jdk.jfr.DataAmount;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String name;
private Long price;
private String nameEN;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getNameEN() {
        return nameEN;
    }

    public Category getCategory() {
        return category;
    }

    public Market getMarket() {
        return market;
    }

    @ManyToOne
private Category category;

@ManyToOne
    private Market market;


    public Product(String name,String nameEN, Long price, Category category, Market market) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.market = market;
        this.nameEN=nameEN;
    }

    public Product() {

    }
}
