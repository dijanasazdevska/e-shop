package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.User;
import mk.ukim.finki.dians.eshop.repository.UserRepository;
import mk.ukim.finki.dians.eshop.service.AuthService;
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
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final AuthService authService;
    private final UserRepository userRepository;
    public CategoryController(CategoryService categoryService, ShoppingCartService shoppingCartService, ProductService productService, AuthService authService, UserRepository userRepository) {
        this.categoryService = categoryService;
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{category}")
    public String getCategoryPage(@PathVariable String category, Model model,@RequestParam(required = false) String language,HttpServletRequest request){

        if(language==null) {
            language="MK";
        }


            model.addAttribute("language", language);


        Category category1 = categoryService.findCategoryByName(language,category);

        model.addAttribute("category", category1);
        model.addAttribute("products", categoryService.findProductsByCategory(category1.getName()));

String c="";
        if(language.equals("MK")) {
            model.addAttribute("newurl", "/category/" + category1.getNameEN() + "?language=EN");
            c=category1.getName();
        }else {
            model.addAttribute("newurl", "/category/" + category1.getName() + "?language=MK");
        c=category1.getNameEN();
        }
        model.addAttribute("url","/category/"+c+"?language="+language);
        if(request.getRemoteUser()!=null){
            model.addAttribute("user",userRepository.findByUsername(request.getRemoteUser()));
        }

        model.addAttribute("bodyContent","category");
        return "master-template";






    }
    @PostMapping("/{category}")
    public String getResultsCategory(@RequestParam String language, HttpServletRequest request, @PathVariable String category){
        request.getSession().setAttribute("products",productService.searchByProducts(request.getParameter("search")));
        return "redirect:/search?language="+language;

    }
    @GetMapping("/{category}/{id}")
    public String addToCart(@PathVariable String category,@RequestParam String language, @PathVariable Long id, @SessionAttribute User user, HttpServletResponse response) throws IOException {

        shoppingCartService.saveOrder(user,productService.searchProductById(id));

        return "redirect:/{category}?language="+language;


    }
}
