package mk.ukim.finki.dians.eshop.repository;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findProductByCategory(Category category);
    List<Product> findProductsByNameContains(String name);


    List<Product> findProductsByNameENContains(String name);

}
