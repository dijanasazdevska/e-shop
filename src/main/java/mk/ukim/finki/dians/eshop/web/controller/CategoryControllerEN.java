package mk.ukim.finki.dians.eshop.web.controller;

import javassist.Translator;
import mk.ukim.finki.dians.eshop.service.CategoryService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("/categories")
public class CategoryControllerEN {

    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoryControllerEN(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String getPage(Model model,HttpServletRequest request){
        model.addAttribute("language","EN");

        model.addAttribute("categories",categoryService.listAll());
        return "categories";



    }
    @PostMapping
    public String getResults(Model model, HttpServletRequest request){

        request.getSession().setAttribute("products",productService.searchByProductsEN(request.getParameter("search")));
        return "redirect:/search";

    }

    @GetMapping("/{category}")
    public String getCategoryPage(@PathVariable String category,Model model){
        model.addAttribute("category",category);

        model.addAttribute("products",categoryService.findProductsByCategoryEN(category));
model.addAttribute("language","EN");

        return "category";
    }
    @PostMapping("/{category}")
    public String getResultsCategory(Model model,HttpServletRequest request,@PathVariable String category){
        request.getSession().setAttribute("products",productService.searchByProductsEN(request.getParameter("search")));
        return "redirect:/search";

    }




}
