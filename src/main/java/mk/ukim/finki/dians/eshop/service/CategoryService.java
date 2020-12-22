package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
List<Category> listAll();
List<Product> findProductsByCategory(String name);
List<Product> findProductsByCategoryEN(String name);
Category findCategoryByName(String name);
Category findCategoryByNameEN(String name);


}
