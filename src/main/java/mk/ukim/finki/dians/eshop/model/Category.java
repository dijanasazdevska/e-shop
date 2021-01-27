package mk.ukim.finki.dians.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;
    @JsonIgnore
    private String imageUrl;
    private String nameEN;
    @OneToMany(mappedBy="category")
    @JsonIgnore
    List<Product> products;

    public Category(String name, String imageUrl,String nameEN) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.nameEN=nameEN;
        this.products=new ArrayList<>();
    }

    public String getNameEN() {
        return nameEN;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", nameEN='" + nameEN + '\'' +
                '}';
    }
}
