package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> listAll();

    public List<Product> findProductsByCategory(Category category);

    public List<Product> searchByProducts(String text);


    public List<Product> searchByProductsEN(String search);
    Product searchProductById(Long id);
}
