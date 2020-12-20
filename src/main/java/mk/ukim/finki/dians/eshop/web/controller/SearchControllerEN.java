package mk.ukim.finki.dians.eshop.web.controller;

import mk.ukim.finki.dians.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/search")
public class SearchControllerEN {
    private final ProductService productService;

    public SearchControllerEN(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getSearch(Model model, HttpServletRequest request){
        model.addAttribute("products",request.getSession().getAttribute("products"));
        model.addAttribute("language","EN");
        return "category"  ;
    }
    @PostMapping
    public String postSearch(@RequestParam String search , HttpServletRequest request){
        request.getSession().setAttribute("products",productService.searchByProductsEN(search));
        return "redirect:/search";
    }
}
