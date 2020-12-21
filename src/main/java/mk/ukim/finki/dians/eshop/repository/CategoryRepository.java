package mk.ukim.finki.dians.eshop.repository;

import mk.ukim.finki.dians.eshop.model.Category;
import mk.ukim.finki.dians.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

Optional<Category> findCategoryByName(String name);
Optional<Category> findCategoryByNameEN(String name);
Category findCategoryById(Long id);
Category findCategoryByNameContains(String name);


}
