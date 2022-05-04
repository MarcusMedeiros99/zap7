package exceptions;

public class UserIsNotAllowedException extends Exception {
    public UserIsNotAllowedException(String message) {
        super(message);
    }
}
