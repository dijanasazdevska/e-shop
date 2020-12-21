package mk.ukim.finki.dians.eshop.model.exceptions;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(String category) {
        super(category+" is not found");
    }
}
