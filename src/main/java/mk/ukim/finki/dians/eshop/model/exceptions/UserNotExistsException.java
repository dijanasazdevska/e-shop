package mk.ukim.finki.dians.eshop.model.exceptions;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException() {
        super("User not exists exception");
    }
}
