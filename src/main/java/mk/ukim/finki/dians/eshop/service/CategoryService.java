package mk.ukim.finki.dians.eshop.service;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;

import java.util.List;
import java.util.Optional;

/** Interfejsot CategoryService sodrzi listi od kategorii i produkti.
 * Ima za cel prvo da ja pronajde baranata kategorija, spored odbraniot jazik (na makedonski ili na angliski) ,
 * pa potoa da prebara kategorii spored ime na soodvetniot jazik sto e odbran.
 */
public interface CategoryService {
List<Category> listAll();
List<Product> findProductsByCategory(String name);
List<Product> findProductsByCategoryEN(String name);
Category findCategoryByNameMK(String name);
Category findCategoryByNameEN(String name);
Category findCategoryByName(String language,String name);


}
