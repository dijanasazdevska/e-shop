package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.User;
import mk.ukim.finki.dians.eshop.service.CategoryService;
import mk.ukim.finki.dians.eshop.service.ProductService;
import mk.ukim.finki.dians.eshop.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public CategoriesController(CategoryService categoryService, ProductService productService, ShoppingCartService shoppingCartService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getPage(@RequestParam(required = false) String language, Model model,HttpServletRequest request){
        if(language==null){
            language="MK";
        }
        model.addAttribute("language",language);
        model.addAttribute("categories",categoryService.listAll());
       if(language.equals("MK")){
           model.addAttribute("newurl","/categories?language=EN");

       }
       else{
           model.addAttribute("newurl","/categories?language=MK");
       }
        model.addAttribute("url","/categories?language="+language);

        model.addAttribute("bodyContent","categories");
        return "master-template";



}
@PostMapping
public String getResults(@RequestParam String language, Model model, HttpServletRequest request){

        request.getSession().setAttribute("products",productService.searchByProducts(request.getParameter("search")));
return "redirect:/search?language="+language;



}






}
