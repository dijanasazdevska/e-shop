package mk.ukim.finki.dians.eshop.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException() {
        super("Invalid user credentials exception");
    }
}
