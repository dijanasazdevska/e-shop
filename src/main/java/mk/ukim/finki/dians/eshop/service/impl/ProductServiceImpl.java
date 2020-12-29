package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.repository.CategoryRepository;
import mk.ukim.finki.dians.eshop.repository.ProductRepository;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductsByCategory(Category category) {
        return productRepository.findProductByCategory(category);
    }

    @Override
    public List<Product> searchByProducts(String text) {
        List<Product> search=productRepository.findProductsByNameContains(text.toUpperCase());
        return  search;
    }

    @Override
    public List<Product> searchByProductsEN(String text) {
        List<Product> search=productRepository.findProductsByNameENContains(text.toUpperCase());
        return search;


    }
    @Override
    public List<Product> searchByProductsByAllNames(String text){
        List<Product> search = searchByProducts(text);
        List<Product> search1=searchByProductsEN(text);
        if(search1.isEmpty())
            return search;
        else
            return search1;
    }

    @Override
    public Product searchProductById(Long id) {
        return productRepository.findProductById(id);
    }
}
