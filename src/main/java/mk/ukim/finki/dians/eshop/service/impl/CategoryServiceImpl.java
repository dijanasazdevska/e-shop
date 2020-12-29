package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.repository.CategoryRepository;
import mk.ukim.finki.dians.eshop.repository.ProductRepository;
import mk.ukim.finki.dians.eshop.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> findProductsByCategory(String name) {
       List<Product> products=productRepository.findProductByCategory(categoryRepository.findCategoryByName(name));
       List<Product> productEN=productRepository.findProductByCategory(categoryRepository.findCategoryByNameEN(name));
   if(!products.isEmpty())
       return products;
   else
       return productEN;

    }

    @Override
    public List<Product> findProductsByCategoryEN(String name) {
        return productRepository.findProductByCategory(categoryRepository.findCategoryByNameEN(name));
    }

    @Override
    public Category findCategoryByNameMK(String name) {
        return categoryRepository.findCategoryByName(name);
    }




    @Override
    public Category findCategoryByNameEN(String name) {

        return categoryRepository.findCategoryByNameEN(name.toUpperCase());
    }

    @Override
    public Category findCategoryByName(String language, String name) {
        if(language.equals("MK")){
            return this.findCategoryByNameMK(name);
        }
        else{
            return this.findCategoryByNameEN(name);
        }
    }


}
