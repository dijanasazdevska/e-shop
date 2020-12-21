package mk.ukim.finki.dians.eshop.web.controller;

import javassist.Translator;
import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;
import mk.ukim.finki.dians.eshop.service.CategoryService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/категории")
public class CategoriesController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoriesController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String getPage(Model model,HttpServletRequest request){
        model.addAttribute("language","MK");
        model.addAttribute("categories",categoryService.listAll());
        return "categories";



}
@PostMapping
public String getResults(Model model, HttpServletRequest request){

        request.getSession().setAttribute("products",productService.searchByProducts(request.getParameter("search")));
return "redirect:/searchMK";

}





}
