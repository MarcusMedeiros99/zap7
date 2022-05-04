package exceptions;

public class UserIsNotAdminException extends UserIsNotAllowedException {
    public UserIsNotAdminException(String message) {
        super(message);
    }
}
