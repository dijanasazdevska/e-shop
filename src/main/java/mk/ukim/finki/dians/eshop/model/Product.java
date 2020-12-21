package mk.ukim.finki.dians.eshop.model;
import lombok.Data;


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
private String imageUrl;

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
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


    public Product(String name,String nameEN, Long price, Category category, Market market,String imageUrl) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.market = market;
        this.nameEN=nameEN;
        this.imageUrl=imageUrl;
    }

    public Product() {

    }
}
