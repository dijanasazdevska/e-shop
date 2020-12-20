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
@RequestMapping("/searchMK")
public class SearchController {

    private final ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getSearch(Model model, HttpServletRequest request){
        model.addAttribute("products",request.getSession().getAttribute("products"));
        model.addAttribute("language","MK");
     return "category"  ;
    }
    @PostMapping
    public String postSearch(@RequestParam String search , HttpServletRequest request){
        request.getSession().setAttribute("products",productService.searchByProducts(search));
        return "redirect:/searchMK";
    }
}
