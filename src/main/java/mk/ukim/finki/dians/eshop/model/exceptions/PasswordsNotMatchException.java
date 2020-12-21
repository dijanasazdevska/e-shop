package mk.ukim.finki.dians.eshop.model.exceptions;

public class PasswordsNotMatchException extends RuntimeException {
    public PasswordsNotMatchException() {
        super("Passwords do not match");
    }
}
