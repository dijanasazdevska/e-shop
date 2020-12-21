package mk.ukim.finki.dians.eshop.web.controller;

import javassist.Translator;
import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.exceptions.CategoryNotFound;
import mk.ukim.finki.dians.eshop.service.CategoryService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoriesControllerEN {

    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoriesControllerEN(CategoryService categoryService, ProductService productService) {
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





}
