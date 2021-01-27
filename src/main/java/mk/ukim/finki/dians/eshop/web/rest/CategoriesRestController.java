package mk.ukim.finki.dians.eshop.web.rest;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.service.CategoryService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesRestController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoriesRestController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.listAll();

    }
    @GetMapping("/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category){

    return categoryService.findProductsByCategory(category);
    }
}
