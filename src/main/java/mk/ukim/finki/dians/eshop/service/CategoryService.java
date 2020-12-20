package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;

import java.util.List;

public interface CategoryService {
List<Category> listAll();
List<Product> findProductsByCategory(String name);
List<Product> findProductsByCategoryEN(String name);


}
