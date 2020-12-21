package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.service.CategoryService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryControllerEN {
    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoryControllerEN(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/{category}")
    public String getCategoryPage(@PathVariable String category, Model model){

        Optional<Category> category1 = categoryService.findCategoryByNameEN(category);
        if(category1.isPresent()) {
            model.addAttribute("category", category1.get());
            model.addAttribute("products", categoryService.findProductsByCategoryEN(category1.get().getNameEN()));

        }
        else{
            model.addAttribute("products",new ArrayList<Product>());
        }

        model.addAttribute("language", "EN");




        return "category";
    }
    @PostMapping("/{category}")
    public String getResultsCategory( HttpServletRequest request, @PathVariable String category){
        request.getSession().setAttribute("products",productService.searchByProductsEN(request.getParameter("search")));
        return "redirect:/search";

    }

}
