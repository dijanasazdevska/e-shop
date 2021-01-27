package mk.ukim.finki.dians.eshop.service.impl;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.repository.CategoryRepository;
import mk.ukim.finki.dians.eshop.repository.ProductRepository;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //Constructor
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    //Se dobiva lista od site produkti.
    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    //Se dobiva lista od site produkti koi pripagjaat vo ista kategorija.
    @Override
    public List<Product> findProductsByCategory(Category category) {
        return productRepository.findProductByCategory(category);
    }

    //Se dobiva lista od site produkti spored vnesenoto ime na makedonski jazik.
    @Override
    public List<Product> searchByProducts(String text) {
        return productRepository.findProductsByNameContains(text.toUpperCase());
    }

    //Se dobiva lista od site produkti spored vnesenoto ime na angliski jazik.
    @Override
    public List<Product> searchByProductsEN(String text) {
         return productRepository.findProductsByNameENContains(text.toUpperCase());
    }

    //Se dobiva lista od site produkti spored vnesenoto ime nezemajki go vo predvid jazikot.
    @Override
    public List<Product> searchByProductsByAllNames(String text){
        if(searchByProductsEN(text).isEmpty())
            return searchByProducts(text);
        else
            return searchByProductsEN(text);
    }

    //Se dobiva odreden produkt spored negoviot ID.
    @Override
    public Product searchProductById(Long id) {
        return productRepository.findProductById(id);
    }
}
